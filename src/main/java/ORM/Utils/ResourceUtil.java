package ORM.Utils;

import ORM.POJO.ResourceTable;

public class ResourceUtil {
    private static ResourceUtil ourInstance = new ResourceUtil();

    private ResourceUtil() {
    }

    public static ResourceUtil getInstance() {
        return ourInstance;
    }

    public ResourceTable getNewSubNode(ResourceTable pnode, String name, String cnname, int istop, Integer order, String urlpath, boolean haschild) {
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
        return node;
    }
}
