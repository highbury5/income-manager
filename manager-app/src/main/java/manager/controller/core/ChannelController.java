package manager.controller.core;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import manager.core.constant.CommonConstant;
import manager.core.message.CommonFailureMessage;
import manager.entity.Account;
import manager.entity.Channel;
import manager.service.ChannelService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 渠道表 前端控制器
 * </p>
 *
 * @author huangduoxiao
 * @since 2020-02-02
 */
@Slf4j
@RestController
@RequestMapping("/api/channel")
@Api(tags="渠道管理")
public class ChannelController {

    @Resource
    ChannelService channelService;

    @ApiOperation(value = "新增渠道")
    @PostMapping("/add")
    public void addChannel(@RequestBody Channel channel){
        log.info("ChannelController -> addChannel");
        channelService.save(channel);
    }

    @ApiOperation(value = "修改渠道")
    @PostMapping("/update")
    public void updateChannel(@RequestBody Channel channel){
        log.info("ChannelController -> updateChannel");
        if(!channelService.updateById(channel)){
            throw CommonFailureMessage.UPDATE_FAILURE.toBizException();
        }
    }

    @ApiOperation(value = "查询渠道列表")
    @GetMapping("/list")
    public IPage<Channel> listChannel(@RequestParam Long currentpage){
        log.info("ChannelController -> listChannel");
        if(currentpage < 1 || null == currentpage){
            throw CommonFailureMessage.ARGUMENT_NOT_VALID.toBizException();
        }
        Page page = new Page(currentpage, CommonConstant.PAGE_SIZE);
        return channelService.page(page);
    }

    @ApiOperation(value = "查询所有渠道（非分页）")
    @GetMapping("/list_all")
    public List<Channel> listAllChannel(){
        log.info("ChannelController -> listChannel");
        return channelService.list();
    }

    @ApiOperation(value = "查询渠道详情")
    @GetMapping("/get/{id}")
    public Channel getChannel(@PathVariable Long id){
        log.info("ChannelController -> getChannel");
        Channel channel = channelService.getById(id);
        if(channel == null){
            throw CommonFailureMessage.READ_FAILURE.toBizException();
        }
        return channel;
    }

}
