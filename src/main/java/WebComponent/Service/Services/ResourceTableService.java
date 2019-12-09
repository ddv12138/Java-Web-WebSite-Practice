package WebComponent.Service.Services;

import GlobalUtils.CommonResult;
import ORM.POJO.ResourceTable;

public interface ResourceTableService {

	int insertNodeByParent(String pid, String name, String cnname, String istopstr, String orderstr, String urlpath, String haschildstr);


	int deleteResource(String sid);

	int updateResNodeInfo(ResourceTable res);

	CommonResult getTabList(String pid, boolean isManage);
}
