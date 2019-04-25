package globalUtils;

import java.util.UUID;

public class UUIDManage {
	public static String getUUIDString() {
		return UUID.randomUUID().toString().replaceAll("-", "").toLowerCase();
	}
	private UUIDManage(){}
}
