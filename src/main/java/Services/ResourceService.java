package Services;

import ORM.Mapper.ResourceMapper;
import ORM.POJO.ResourceTable;
import com.alibaba.fastjson.JSON;
import globalUtils.CommonResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional
public class ResourceService {
    @Resource
    private ResourceMapper mapper;

    public int insertNodeByParent(String pid, String name, String cnname, String istopstr, String orderstr, String urlpath, String haschildstr) {
        Integer pnodeid = null;
        if (null != pid) {
            pnodeid = Integer.parseInt(pid);
        }
        if (null == pnodeid) return -1;
        ResourceTable pnode = mapper.selectByID(pnodeid);
        ResourceTable node = new ResourceTable();
        node.setName(name);
        node.setCnname(cnname);
        node.setUrlpath(urlpath);

        if (null != istopstr) {
            node.setIstop(Integer.parseInt(istopstr));
        }
        if (null != orderstr) {
            node.setOrder(Integer.parseInt(orderstr));
        }
        if (null != haschildstr) {
            node.setHaschild("1".equals(haschildstr));
        }

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

    public CommonResult getTabList(String pid, boolean ismanage) {
        CommonResult resp;
        if ((com.mysql.cj.util.StringUtils.isNullOrEmpty(pid)) && !ismanage) {
            ResourceTable rt = new ResourceTable();
            rt.setIstop(1);
            ResourceTable[] res = mapper.selectByExample(rt);
            resp = new CommonResult(true, "sucess", JSON.toJSONString(res));
        } else if ((com.mysql.cj.util.StringUtils.isNullOrEmpty(pid)) && ismanage) {
            ResourceTable res = mapper.selectByID(1);
            resp = new CommonResult(true, "sucess", JSON.toJSONString(res));
        } else {
            ResourceTable pnode = mapper.selectByID(Integer.parseInt(pid));
            ResourceTable[] res = mapper.selectNextLevelNode(pnode);
            resp = new CommonResult(true, "sucess", JSON.toJSONString(res));
        }
        return resp;
    }
}
