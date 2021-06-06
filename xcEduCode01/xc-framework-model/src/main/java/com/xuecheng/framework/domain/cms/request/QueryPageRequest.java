package com.xuecheng.framework.domain.cms.request;

import com.xuecheng.framework.model.request.RequestData;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;


@Data
@ToString

public class QueryPageRequest extends RequestData {


    @ApiModelProperty("站点id")
    //站点id
    private String siteId;
    @ApiModelProperty("页面id")
    //页面id
    private String pageId;
    @ApiModelProperty("页面名称")
    //页面名称
    private String pageName;
    @ApiModelProperty("页面别名")
    //别名
    private String pageAliase;
    @ApiModelProperty("模版id")
    //模版id
    private String templateId;
}
