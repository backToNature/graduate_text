package com.swust.kelab.web.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.swust.kelab.domain.User;
import com.swust.kelab.model.CommonQuery;
import com.swust.kelab.model.QueryData;
import com.swust.kelab.service.UserService;
import com.swust.kelab.web.json.JsonAndView;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/view",method=RequestMethod.GET)
	public JsonAndView viewOne(User user){
		 JsonAndView jv = new JsonAndView();
		 int id =user.getId();
		 ///
		 jv.setRet(true);
		 return jv;
	}
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public JsonAndView addOne(User user){
		 JsonAndView jv = new JsonAndView();
		 user.setTime(new Date());
		 try {
			 userService.addOneUser(user);
		} catch (Exception e) {
			//异常处理
		}
		
		 int id =user.getId();
		 jv.setRet(true);
		 return jv;
	}
	
	@RequestMapping(value="/delete",method=RequestMethod.GET)
	public JsonAndView deleteOne(int id){
		 JsonAndView jv = new JsonAndView();
		 try {
				int flag = userService.deleteOneUser(id);
		} catch (Exception e) {
			// TODO: handle exception
		}

		 jv.setRet(true);
		 return jv;
	}
	
	@RequestMapping(value="/update",method=RequestMethod.GET)
	public JsonAndView updateOne(User user){
		 JsonAndView jv = new JsonAndView();
		 user.setTime(new Date());
		 try {
			 userService.updateOne(user);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		 
		 int id =user.getId();
		 ///
		 jv.setRet(true);
		 return jv;
	}
	
	@RequestMapping(value="/query",method=RequestMethod.GET)
	public JsonAndView query(CommonQuery query,String name){
		 JsonAndView jv = new JsonAndView();
		 try {
		QueryData result=  userService.queryByPage(query, name);
		 ///
		  if (result == null) {
	            jv.setRet(false);
	            jv.setErrcode(1);
	            jv.setErrmsg("error");
	        } else {
	            jv.addData("totalPage", result.getTotalPage());
	            jv.addData("totalCount", result.getTotalCount());
	            jv.addData("pageData", result.getPageData());
	        }
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	        return jv;
	}
}
