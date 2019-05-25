package Services;

import ORM.Mapper.ResourceMapper;
import ORM.POJO.ResourceTable;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ResourceService {
    @Resource
    private ResourceMapper mapper;

    public int insertNodeByParent(int pid, String name, String cnname, int istop, Integer order, String urlpath, boolean haschild) {
        ResourceTable pnode = mapper.selectByID(pid);
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
}
