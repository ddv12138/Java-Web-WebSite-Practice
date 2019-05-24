package Services;

import ORM.Mapper.ResourceMapper;
import ORM.POJO.ResourceTable;
import ORM.Utils.Condition;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class ResourceService {
    @Resource
    ResourceMapper mapper;

    public int insertNodeByParent(int pid, String name, String cnname, int istop, Integer order, String urlpath, boolean haschild) {
        ResourceTable pnode = this.selectByID(pid);
        ResourceTable node = new ResourceTable();
        node.setLeftvalue(pnode.getLeftvalue() + 1);
        node.setRightvalue(pnode.getLeftvalue() + 2);
        node.setLevel(pnode.getLevel() + 1);
        node.setName(name);
        node.setCnname(cnname);
        node.setIstop(istop);
        node.setOrder(order);
        node.setUrlpath(urlpath);
        node.setHaschild(haschild);
        return this.InsertByAllvalue(node);
    }


    public int deleteResource(String sid) {
        if (StringUtils.isEmpty(sid)) {
            throw new RuntimeException("参数为空");
        }
        int id = Integer.parseInt(sid);
        if (id == 1 || id == 2) {
            throw new RuntimeException("不允许删除的节点");
        }
        ResourceTable res = this.selectByID(id);
        if (null == res) {
            throw new RuntimeException("节点不存在");
        }
        return this.deleteRes(res, res.getRightvalue() - res.getLeftvalue() + 1);
    }

    public int InsertByAllvalue(ResourceTable res) {
        return mapper.InsertByAllvalue(res);
    }

    public ResourceTable[] selectByExample(ResourceTable res) {
        return mapper.selectByExample(res);
    }

    public ResourceTable[] updateByCondition(ResourceTable res, List<Condition> conds) {
        return mapper.updateByCondition(res, conds);
    }

    public ResourceTable[] selectByCondition(List<Condition> conds) {
        return mapper.selectByCondition(conds);
    }

    public ResourceTable selectByID(int id) {
        return mapper.selectByID(id);
    }

    public ResourceTable[] selectNextLevelNode(ResourceTable res) {
        return mapper.selectNextLevelNode(res);
    }

    public ResourceTable[] selectAllSubNode(ResourceTable res) {
        return mapper.selectAllSubNode(res);
    }

    public int updateByExample(ResourceTable res) {
        return mapper.updateByExample(res);
    }

    public Integer insertByParent(ResourceTable pnode, ResourceTable node) {
        return mapper.insertByParent(pnode, node);
    }

    public Integer deleteRes(ResourceTable node, int offset) {
        return mapper.deleteRes(node, offset);
    }

    public Map selectMapByID(int id) {
        return mapper.selectMapByID(id);
    }

    public Map listResource() {
        return mapper.listResource();
    }
}
