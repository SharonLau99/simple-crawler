package com.example.webmagic.pipeline;

import com.example.webmagic.dao.ZhihuHotRepository;
import com.example.webmagic.entity.zhihu.ZhihuHotItem;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author yinfelix
 * @date 2020/6/18
 */
@Component
@Slf4j
public class ZhihuHotPipeline extends SimpleListPersistencePipeline<ZhihuHotItem> {
    private final ZhihuHotRepository repository;

    public ZhihuHotPipeline(ZhihuHotRepository repository) {
        this.repository = repository;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void process(ZhihuHotItem zhihuHotItem) {
        ZhihuHotItem saveResult = repository.saveAndFlush(zhihuHotItem);
        if (saveResult.getId() == null) {
            log.error(zhihuHotItem.getQId() + "暂时无法保存至数据库！");
        } else {
            log.debug(zhihuHotItem.getQId() + "已保存");
        }
    }
}
