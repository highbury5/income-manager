package manager.controller.busi;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import manager.core.constant.CommonConstant;
import manager.core.constant.StringPool;
import manager.core.message.CommonFailureMessage;
import manager.core.message.FileUploadResult;
import manager.entity.Income;
import manager.entity.IncomeChannel;
import manager.entity.ProcessDetail;
import manager.entity.dto.*;
import manager.mapper.IncomeChannelMapper;
import manager.service.FileService;
import manager.service.IncomeChannelService;
import manager.service.IncomeService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 进件表 前端控制器
 * </p>
 *
 * @author huangduoxiao
 * @since 2020-02-05
 */
@Slf4j
@RestController
@RequestMapping("/api/income")
@Api(tags = "进件控制器")
public class IncomeController {

    @Resource
    IncomeService incomeService;

    @Resource
    FileService fileService;

    @Resource
    IncomeChannelService incomeChannelService;

    @ApiOperation(value = "新增进件")
    @PostMapping("/add")
    public void add(@RequestBody IncomeDto incomeDto){
        log.info("IncomeController -> add");
        log.info(incomeDto.toString());
        log.info(JSON.toJSONString(incomeDto));
        incomeService.addIncome(incomeDto);
    }

    @ApiOperation(value = "修改进件")
    @PostMapping("/update")
    public void updateIncome(@RequestBody IncomeDto incomeDto){
        log.info("IncomeController -> updateIncome");
        incomeService.updateIncome(incomeDto);

    }

    @ApiOperation(value="删除进件")
    @GetMapping("/delete/{id}")
    public void deleteIncome(@PathVariable("id") Long id){
        log.info("IncomeController -> deleteIncome");
        if(!incomeService.removeById(id)){
            throw CommonFailureMessage.DELETE_FAILURE.toBizException();
        }
    }

    @ApiOperation(value = "查询进件列表")
    @GetMapping("/list")
    public IPage<Income> listIncome(@RequestParam Long currentpage, HttpServletRequest request){
        log.info("IncomeController -> listIncome");
        Long userId = (Long)request.getAttribute(StringPool.LOGIN_USER_KEY);
        log.info("user id : {}",userId);
        return incomeService.listIncome(currentpage,userId);
    }


    @ApiOperation(value = "查询进件详情")
    @GetMapping("/get/{id}")
    public IncomeDto getIncome(@PathVariable Long id){
        log.info("IncomeController -> getIncome");
        return incomeService.getIncome(id);
    }

    @ApiOperation(value = "文件上传")
    @PostMapping("/upload")
    public FileUploadResult<Object> uploadFile(@RequestParam MultipartFile file){
        log.info("IncomeController -> uploadFile");
        try {
            HashMap<String, String> result = new HashMap<>();
            result.put("fileName", fileService.uploadFile(file));
            result.put("name",file.getOriginalFilename());
            return new FileUploadResult<>("200","文件上传成功",result,FileUploadResult.SUCCESS);

        }catch (IOException e){
            log.error(e.getMessage());
            return  new FileUploadResult<>("222","文件上传失败",e.getMessage(),FileUploadResult.ERROR);
        }

    }

    @ApiOperation(value = "文件下载")
    @GetMapping("/download")
    public void downFile(@RequestParam String fileName, HttpServletResponse response){
        log.info("IncomeController -> uploadFile");
        fileService.downloadFile(fileName,response);
    }

    @ApiOperation(value = "修改进件状态&新增处理意见")
    @PostMapping("/update_status")
    public void addOpinion(@RequestBody ProcessDetail processDetail){
        log.info("IncomeController -> addOpinion");
        incomeService.addOpinion(processDetail);
    }

    @ApiOperation(value = "修改进件状态&维护付款信息&新增处理意见")
    @PostMapping("/update_status_with_payment")
    public void channelConfirm(@RequestBody IncomePaymentDto incomePaymentDto){
        log.info("IncomeController -> channelConfirm");
        incomeService.channelConfirm(incomePaymentDto);
    }

    @ApiOperation(value = "查询处理意见列表")
    @GetMapping("/list_opinion/{id}")
    public List<ProcessDetail> listOpinion(@PathVariable Long id){
        log.info("IncomeController -> listOpinion");
        return incomeService.listOpinion(id);
    }

    @ApiOperation(value = "短信发送进件要素")
    @PostMapping("/send_message")
    public void sendMessage(@RequestBody IncomeChannelDto incomeChannelDto){
        log.info("IncomeController -> send_message");
        incomeService.sendMessage(incomeChannelDto);
    }

    @ApiOperation(value = "查询渠道咨询列表")
    @GetMapping("/list_channel_adviory")
    public IPage<ChannelAdvioryDto> listChannelAdviory(@RequestParam Long currentpage){
        log.info("IncomeController -> listChannelAdviory");
        return incomeService.listChannelAdviory(currentpage);

    }

    @ApiOperation(value = "渠道认领")
    @GetMapping("/channel_adviory/{id}")
    public void channelAdviory(@RequestParam Long id){
        log.info("IncomeController -> listChannelAdviory");
        incomeService.channelAdviory(id);

    }

    @ApiOperation(value = "查询所有认领的渠道")
    @GetMapping("/list_claim_channel/{id}")
    public List<RecevieChannelDto> listIncomeChannel(@PathVariable("id") Long incomeId){
        log.info("IncomeController -> listOpinion,income id [{}]",incomeId);
        return incomeChannelService.listRecevieChannel(incomeId) ;
    }

    @ApiOperation(value = "按日期查询进件列表--非分页")
    @PostMapping("/list_by_date")
    public List<IncomeListDto> listIncomeByDate(@RequestBody IncomeListDto incomeListDto){
        log.info("IncomeController -> listIncomeByDate");
        return incomeService.listByDate(incomeListDto.getStartDate(),incomeListDto.getEndDate()) ;
    }



}
