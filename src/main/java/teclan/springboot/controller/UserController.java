package teclan.springboot.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import teclan.springboot.model.User;

@RestController  
public class UserController {

	@Resource
	private JdbcTemplate jdbcTemplate;
	
	@RequestMapping("/user/query")
	public List<Map<String,Object>> query() {
		return jdbcTemplate.queryForList("select * from users limit 1,3");
	}
}
