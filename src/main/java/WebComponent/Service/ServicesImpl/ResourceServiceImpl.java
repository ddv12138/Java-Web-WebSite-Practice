package WebComponent.Service.ServicesImpl;

import GlobalUtils.CommonResult;
import ORM.Mapper.ResourceMapper;
import ORM.POJO.ResourceTable;
import WebComponent.Service.Services.ResourceService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Optional;

@Service("ResourceService")
@Transactional
public class ResourceServiceImpl implements ResourceService {
	@Resource
	private ResourceMapper mapper;

	public int insertNodeByParent(String pid, String name, String cnname, String istopstr, String orderstr, String urlpath, String haschildstr) {
		Optional<Integer> pnodeid = Optional.ofNullable(pid).map(id -> Integer.parseInt(pid));
		ResourceTable pnode = mapper.selectByID(pnodeid.get());
		ResourceTable node = new ResourceTable();
		node.setName(name);
		node.setCnname(cnname);
		node.setUrlpath(urlpath);

		node.setIstop(Integer.parseInt(Optional.ofNullable(istopstr).get()));
		node.setHaschild(Optional.ofNullable(haschildstr).map(hc -> hc.equals("1")).get());

		node.setLeftvalue(pnode.getLeftvalue() + 1);
		node.setRightvalue(pnode.getLeftvalue() + 2);
		node.setLevel(pnode.getLevel() + 1);
		return mapper.insertByParent(pnode, node);
	}


	public int deleteResource(String sid) {
		if (StringUtils.isEmpty(sid)) {
			throw new RuntimeException("参数为空");
		}
		int id = Integer.parseInt(sid);
		if (id == 1 || id == 2) {
			throw new RuntimeException("不允许删除的节点");
		}
		ResourceTable res = mapper.selectByID(id);
		if (null == res) {
			throw new RuntimeException("节点不存在");
		}
		return mapper.deleteRes(res, res.getRightvalue() - res.getLeftvalue() + 1);
	}

	public int updateResNodeInfo(ResourceTable res) {
		return mapper.updateByExample(res);
	}

	public CommonResult getTabList(String pid, boolean isManage) {
		CommonResult resp;
		if ((com.mysql.cj.util.StringUtils.isNullOrEmpty(pid)) && !isManage) {
			ResourceTable rt = new ResourceTable();
			rt.setIstop(1);
			ResourceTable[] res = mapper.selectByExample(rt);
			resp = new CommonResult(true, "sucess", res);
		} else if ((com.mysql.cj.util.StringUtils.isNullOrEmpty(pid)) && isManage) {
			ResourceTable res = mapper.selectByID(1);
			resp = new CommonResult(true, "sucess", res);
		} else {
			ResourceTable pnode = mapper.selectByID(Integer.parseInt(pid));
			ResourceTable[] res = mapper.selectNextLevelNode(pnode);
			if (res.length > 0) {
				resp = new CommonResult(true, "sucess", res);
			} else {
				resp = new CommonResult(true, "sucess", null);
			}
		}
		return resp;
	}
}
