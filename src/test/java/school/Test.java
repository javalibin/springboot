package school;

import com.alibaba.druid.support.json.JSONParser;
import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.JSON;
import com.school.manage.model.User;

public class Test {

	public static void main(String[] args) {
		User user = new User();
		user.setAge(18);
		user.setBirthday("1996-11-20");
		user.setJurisdictionId(0);
		user.setName("我的个乖乖");
		user.setPwd("a111111");
		user.setSex("男");
		System.out.println(JSON.toJSONString(user));
	}

}
