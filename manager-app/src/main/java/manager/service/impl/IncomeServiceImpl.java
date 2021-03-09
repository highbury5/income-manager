package manager.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import manager.core.constant.CommonConstant;
import manager.core.constant.IncomeStatus;
import manager.core.constant.StringPool;
import manager.core.message.CommonFailureMessage;
import manager.core.sms.SendMsgUtil;
import manager.entity.*;
import manager.entity.dto.*;
import manager.mapper.*;
import manager.service.IncomeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import manager.util.RoleUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

/**
 * <p>
 * 进件表 服务实现类
 * </p>
 *
 * @author huangduoxiao
 * @since 2020-02-05
 */
@Slf4j
@Service
@Transactional(propagation= Propagation.REQUIRED)
public class IncomeServiceImpl extends ServiceImpl<IncomeMapper, Income> implements IncomeService {

    @Resource
    IncomeMapper incomeMapper;

    @Resource
    CustomMapper customMapper;

    @Resource
    AccountMapper accountMapper;

    @Resource
    ProcessDetailMapper processDetailMapper;

    @Resource
    AccountRoleMapper accountRoleMapper;

    @Resource
    ChannelMapper channelMapper;

    @Resource
    IncomeChannelMapper incomeChannelMapper;

    @Resource
    ProductMapper productMapper;

    @Resource
    IncomePaymentMapper incomePaymentMapper;

    @Resource
    CollateralDetailMapper collateralDetailMapper;

    private static String PREFIX = "DQ";

    private static String SMS_TEMPLATE_ID = "178299";

    /**
     * 新增进件信息
     * @param incomeDto
     */
    public void addIncome(IncomeDto incomeDto){

        Income income = new Income();
        BeanUtils.copyProperties(incomeDto,income);

        //TODO 待放开新增企业信息
        //判断若为新企业，新增企业信息记录
        List<Custom> customs =  customMapper.getByNameAndPerson(income.getCorpName(),income.getLegalPerson());
        if(customs.isEmpty()){
            addCustomInfo(income);
        }

        //新增进件记录
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String date = dateFormat.format(new Date());
        String bussnissNo = PREFIX + date;
        log.info("bussniss no is [{}]",bussnissNo);
        LocalDateTime now = LocalDateTime.now();
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        income.setBusinessNo(bussnissNo);
        income.setStatus(IncomeStatus.TO_BE_CLAIMED.getValue());
        income.setProcessorId((Long)request.getAttribute(StringPool.LOGIN_USER_KEY));
        Account account = accountMapper.selectById(income.getProcessorId());
        if(account == null){
            throw CommonFailureMessage.ACCOUNT_NOT_EXIST.toBizException();
        }
        income.setProcessor(account.getAccountName());
        income.setProducterId(account.getId());
        income.setProcessTime(now);

        LocalDate startDate = LocalDate.now();
        income.setStartDate(startDate);
        incomeMapper.insert(income);

        //新增进件处理意见详情
        ProcessDetail processDetail = new ProcessDetail();
        processDetail.setIncomeId(income.getId());
        processDetail.setComment(income.getComment());
        processDetail.setProcessor(income.getProcessor());
        processDetail.setProcessTime(now);
        processDetail.setStatus(IncomeStatus.TO_BE_CLAIMED.getValue());
        //TODO 待添加耗时
        processDetailMapper.insert(processDetail);

        //处理抵押物明细
        List<CollateralDetail> list = incomeDto.getCollateralDetails();
        for(CollateralDetail collateralDetail : list){
            collateralDetail.setIncomeId(income.getId());
            collateralDetailMapper.insert(collateralDetail);
        }

    }

    /**
     * 修改进件信息
     * @param incomeDto
     */
    public void updateIncome(IncomeDto incomeDto){
        Income income = new Income();
        BeanUtils.copyProperties(incomeDto,income);

        if(incomeMapper.updateById(income) <= 0){
            throw CommonFailureMessage.UPDATE_FAILURE.toBizException();
        }
        incomeMapper.updateById(income);

        //删除原抵押物明细
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("income_id",income.getId());
        collateralDetailMapper.delete(queryWrapper);

        //新增抵押物明细
        List<CollateralDetail> list = incomeDto.getCollateralDetails();
        for(CollateralDetail collateralDetail : list){
            collateralDetail.setIncomeId(income.getId());
            collateralDetailMapper.insert(collateralDetail);
        }

    }

    //查询进件详情
    public IncomeDto getIncome(Long id){
        //TODO 待优化关联查询
        Income income = incomeMapper.selectById(id);
        if(income == null){
            throw CommonFailureMessage.INCOME_NOT_EXIST.toBizException();
        }


        IncomeDto incomeDto = new IncomeDto();
        BeanUtils.copyProperties(income,incomeDto);
        Product product = productMapper.selectById(income.getProductId());
        if(product != null){
            incomeDto.setProductName(product.getName());
        }
        Account account = accountMapper.selectById(income.getProducterId());
        if(account != null){
            incomeDto.setClientName(account.getAccountName());
        }

        IncomePaymentDto incomePaymentDto = incomePaymentMapper.selectIncomePayment(id);
        if(incomePaymentDto != null){
            incomeDto.setIncomePaymentDto(incomePaymentDto);
        }

        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("income_id",income.getId());
        List<CollateralDetail> list = collateralDetailMapper.selectList(queryWrapper);
        incomeDto.setCollateralDetails(list);

        return incomeDto;
    }

    /**
     * 修改进件状态、登记处理历史
     * @param processDetail
     */
    public void addOpinion(ProcessDetail processDetail){
        //获取当前处理人
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        Long userId = (Long)request.getAttribute(StringPool.LOGIN_USER_KEY);
        Account account = accountMapper.selectById(userId);
        if(account == null){
            throw CommonFailureMessage.ACCOUNT_NOT_EXIST.toBizException();
        }

        //获取当前时间
        LocalDateTime now = LocalDateTime.now();

        //判断进件是否存在
        Income oldIncome = incomeMapper.selectById(processDetail.getIncomeId());
        if(null == oldIncome){
            throw CommonFailureMessage.INCOME_NOT_EXIST.toBizException();
        }

        //修改进件状态、信息
        Income income = new Income();

        //记录上一处理人
        income.setLastProcessorId(oldIncome.getProcessorId());
        income.setLastProcessor(oldIncome.getProcessor());

        //如果是退回后重新提交，处理人为退回者，否则为当前用户
        int status = oldIncome.getStatus();
        if(status == IncomeStatus.FINAL_REVIEW_RETURN.getValue()||status == IncomeStatus.SECOND_REVIEW_RETURN.getValue()||
           status == IncomeStatus.FINAL_REVIEW_RETURN.getValue()){
            income.setProcessorId(oldIncome.getLastProcessorId());
            income.setProcessor(oldIncome.getProcessor());
        }else{
            income.setProcessorId(userId);
            income.setProcessor(account.getAccountName());
        }

        income.setId(processDetail.getIncomeId());
        income.setStatus(processDetail.getStatus());
        income.setComment(processDetail.getComment());
        income.setProcessTime(LocalDateTime.now());

        //如果为结束状态，记录结束日期
        if(income.getStatus() == IncomeStatus.END.getValue()){
            LocalDate endDate = LocalDate.now();
            income.setEndDate(endDate);
        }
        incomeMapper.updateById(income);

        //新增进件处理意见明细记录
        //TODO 处理人、时间后台自行计算
        processDetail.setProcessTime(now);
        processDetail.setProcessor(account.getAccountName());
        processDetailMapper.insert(processDetail);
    }

    /**
     * 修改进件状态、付款信息、登记处理历史
     * @param incomePaymentDto
     */
    public void channelConfirm(IncomePaymentDto incomePaymentDto){
        //修改状态
        Income income = new Income();
        income.setId(incomePaymentDto.getIncomeId());
        income.setStatus(incomePaymentDto.getStatus());
        income.setComment(incomePaymentDto.getComment());
        this.updateIncomeStatus(income);

        //新增or修改数据
        IncomePayment incomePayment = new IncomePayment();
        BeanUtils.copyProperties(incomePaymentDto,incomePayment);
        if(incomePaymentMapper.updateById(incomePayment) <= 0){
            log.info("incomePayment记录不存在，新增记录");
            if(incomePaymentMapper.insert(incomePayment) <=0){
                throw CommonFailureMessage.ADD_FAILURE.toBizException();
            }
        }

        //新增意见明细
        //TODO 新增意见明细

    }

    public List<ProcessDetail> listOpinion(Long incomeId){
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("income_id",incomeId);
        return processDetailMapper.selectList(queryWrapper);
    }

    private void addCustomInfo(Income income){
        Custom custom = new Custom();
        custom.setCorpName(income.getCorpName());
        custom.setLegalPerson(income.getLegalPerson());
        custom.setSetupDate(income.getSetupDate());
        custom.setRegisteredCapital(income.getRegisteredCapital());
        custom.setPaidUpCapital(income.getPaidUpCapital());
        custom.setBusinessFlow(income.getBusinessFlow());
        custom.setLiabilities(income.getLiabilities());
        custom.setAddress(income.getAddress());
        customMapper.insert(custom);
    }

    /**
     * 查询进件列表
     * @param currentPage
     * @param userId
     * @return
     */
    public IPage<Income> listIncome(Long currentPage,Long userId){
        if(currentPage < 1 || null == currentPage){
            throw CommonFailureMessage.ARGUMENT_NOT_VALID.toBizException();
        }
        //获取角色
        List<AccountRole> list = this.getAccountRole(userId);

        Page page = new Page(currentPage, CommonConstant.PAGE_SIZE);
        QueryWrapper queryWrapper = new QueryWrapper();

        if(RoleUtil.isContantRole(2L,list)){
            //产品经理
            queryWrapper.eq("producter_id",userId);

        }else if(RoleUtil.isContantRole(8L,list)){
            //渠道人员
            queryWrapper.eq("status",7);
            Calendar calendar = Calendar.getInstance();
            calendar.before(Calendar.MINUTE - 48);
            Date date = calendar.getTime();
            queryWrapper.ge("process_time",date);
        }
        //其他人员查询全部

        IPage<Income> incomes = incomeMapper.selectPage(page,queryWrapper);

        return incomes;
    }

    /**
     * 短信发送进件要素给渠道
     * @param incomeChannelDto
     */
    public void sendMessage(IncomeChannelDto incomeChannelDto){
        //判断进件是否存在
        Income income = incomeMapper.selectById(incomeChannelDto.getIncomeId());
        if(income == null){
            throw CommonFailureMessage.INCOME_NOT_EXIST.toBizException();
        }

        //判断进件状态是否为渠道咨询（已终审）
        if(income.getStatus() != IncomeStatus.CHANNEL_ADVIORY.getValue() &&
           income.getStatus() != IncomeStatus.TO_BE_CHANNEL_CONFIRM.getValue()){
            throw CommonFailureMessage.INCOME_STATUS_ERROR.toBizException();
        }


        for(Long channelId : incomeChannelDto.getChannelIds()){
            Channel channel = channelMapper.selectById(channelId);
            //判断渠道是否存在
            if(channel == null){
               throw CommonFailureMessage.CHANNEL_NOT_EXIST.toBizException();
            }

            //判断进件渠道是否存在
            IncomeChannel incomeChannel = new IncomeChannel();
            incomeChannel.setIncomeId(incomeChannelDto.getIncomeId());
            incomeChannel.setChannelId(channelId);


            QueryWrapper queryWrapper = new QueryWrapper();
            Long incomeId = incomeChannel.getIncomeId();
            log.info("income id: {},channel id: {}",incomeId,channelId);
            queryWrapper.eq("income_id",incomeId);
            queryWrapper.eq("channel_id",channelId);
            List<IncomeChannel> list = incomeChannelMapper.selectList(queryWrapper);
            if(list.isEmpty()){
                //新增
                log.info("新增进件待认领渠道，渠道{}",channel.getName());
                incomeChannel.setStatus(1);
                incomeChannelMapper.insert(incomeChannel);
            }

        }
        sendMessageAsync(income.getId());
    }

    @Async
    public void sendMessageAsync(Long incomeId){
        List<HashMap<String,String>> list = incomeChannelMapper.selectNoSendChannel(incomeId);
        for(HashMap map : list){
            int channelId = (int)map.get("channel_id");
            String phone = (String)map.get("phone");
            if(!StringUtils.isEmpty(channelId) && !StringUtils.isEmpty(phone)){
                try {
                    SendMsgUtil.sendsms(SMS_TEMPLATE_ID, phone);

                    QueryWrapper queryWrapper = new QueryWrapper();
                    queryWrapper.eq("income_id",incomeId);
                    queryWrapper.eq("channel_id",channelId);
                    IncomeChannel incomeChannel = new IncomeChannel();
                    incomeChannel.setStatus(2);
                    incomeChannelMapper.update(incomeChannel,queryWrapper);
                }catch (Exception e){
                    e.printStackTrace();
                    log.error("发送渠道通知短信失败，进件号【{}】，渠道号【{}】，手机号【{}】",incomeId,channelId,phone);
                }
            }else{
                log.error("渠道联系方式错误");
            }
        }


    }


    /**
     * 查询渠道咨询列表
     * @param currentpage
     * @return
     */
    public IPage<ChannelAdvioryDto> listChannelAdviory(Long currentpage){
        Page page = new Page(currentpage, CommonConstant.PAGE_SIZE);

        //获取当前用户所属渠道
        Channel channel = this.getCurrentChannel();
        Long channelId = channel.getId();

        List<ChannelAdvioryDto> channelAdvioryDtos = incomeChannelMapper.selectChannelAdviory(page,channelId);
        page.setRecords(channelAdvioryDtos);

        return page;
    }

    /**
     * 渠道认领
     * @param incomeId
     */
    public void channelAdviory(Long incomeId){
        //获取当前用户所属渠道
        Channel channel = this.getCurrentChannel();
        Long channelId = channel.getId();

        //更新渠道认领记录
        IncomeChannel incomeChannel = new IncomeChannel();
        incomeChannel.setStatus(3); //已认领
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("income_id",incomeId);
        queryWrapper.eq("channel_id",channelId);
        if(incomeChannelMapper.update(incomeChannel,queryWrapper) <= 0){
            throw CommonFailureMessage.INCOMECHANNEL_NOT_EXIST.toBizException();
        }

        //更新进件状态
        Income income = new Income();
        income.setId(incomeId);
        income.setStatus(IncomeStatus.TO_BE_CHANNEL_CONFIRM.getValue());
        incomeMapper.updateById(income);

        //新增意见
        //TODO 待添加意见明细记录

    }


    public List<AccountRole> getAccountRole(Long userId){
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("account_id",userId);
        return accountRoleMapper.selectList(queryWrapper);
    }



    public Channel getCurrentChannel(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        Long userId= (Long)request.getAttribute(StringPool.LOGIN_USER_KEY);
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("account_id",userId);
        List<Channel> channels = channelMapper.selectList(queryWrapper);
        if(channels.isEmpty()){
            throw CommonFailureMessage.CHANNEL_NOT_MATCH.toBizException();
        }
        return channels.get(0);
    }



    /**
     * 状态流转时修改进件信息
     * @param income
     */
    private void updateIncomeStatus(Income income){
        Income incomeVo = new Income();
        incomeVo.setId(income.getId());
        incomeVo.setStatus(income.getStatus());
        incomeVo.setComment(income.getComment());

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        income.setProcessorId((Long)request.getAttribute(StringPool.LOGIN_USER_KEY));
        Account account = accountMapper.selectById(income.getProcessorId());
        if(account == null){
            throw CommonFailureMessage.ACCOUNT_NOT_EXIST.toBizException();
        }
        income.setProcessor(account.getAccountName());
        income.setProcessTime(LocalDateTime.now());

        if(incomeMapper.updateById(incomeVo) <= 0){
            throw CommonFailureMessage.INCOME_NOT_EXIST.toBizException();
        }
    }

    public List<IncomeListDto> listByDate(LocalDate startDate, LocalDate endDate){
        return incomeMapper.listByDate(startDate,endDate);
    }


}
