package ddvudo.globalUtils;

import java.util.UUID;

public class UUIDManage {
	public String getUUIDString() {
		String uuid = UUID.randomUUID().toString().replaceAll("-", "").toLowerCase();
		return uuid;
	}
	public static UUIDManage getInstance() {
		return new UUIDManage();
	}
}
