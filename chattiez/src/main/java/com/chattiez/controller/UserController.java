package com.chattiez.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;




import com.chattiez.dao.BlogDao;

import com.chattiez.dao.UsersDao;
import com.chattiez.model.Blog;

import com.chattiez.model.Users;
@RestController
public class UserController {
	@Autowired
	UsersDao usersdao;
	
	@Autowired
	BlogDao blogdao;
	
	
	
	@RequestMapping(value="/fileUpload", method=RequestMethod.POST)
	public void saveUser(@RequestParam("file") MultipartFile file,@RequestParam("email") String email,@RequestParam("password") String password,@RequestParam("username") String username,@RequestParam("mobile") String mobile) 
	{
		System.out.println("Inside");
		System.out.println("file:"+file);
		System.out.println("Email:"+email+"\t"+password+"\t"+username+"\t"+mobile);
	Users user=new Users();
	user.setEmail(email);
	user.setPassword(password);	
	user.setUsername(username);
	user.setMobile(mobile);
	usersdao.registerUser(user);
	Path path=Paths.get("D://me//Chatify//WebContent//images//"+email+".jpg");
	if(file!=null)
	{
		try {
			file.transferTo(new File(path.toString()));
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	}
	
	
	@RequestMapping(value="findFriends/{email}",headers="accept=Application/json",method=RequestMethod.GET)
	public List<Users> findFriends(@PathVariable String email)
	{
		List<Users> user=usersdao.findFriends(email);
		return user;
		
	}
	@RequestMapping(value="/viewBlogs/{posted_By}",headers="accept=Application/json",method=RequestMethod.GET)
	public List<Blog> viewBlogs(@PathVariable("posted_By") String posted_By)
	{
		System.out.println("given name:"+posted_By);
		List<Blog> blogs= blogdao.viewMyBlogs(posted_By);
		return blogs;
	}
	
	@RequestMapping(value="/viewAllBlogs",headers="accept=Application/json",method=RequestMethod.GET)
	public List<Blog> viewAllBlogs()
	{
		List<Blog> blogs=blogdao.viewBlogs();
		return blogs;
	}
	
	
	@RequestMapping(value="/createBlog",headers="accept=Application/json",method=RequestMethod.POST)
	public void saveBlog(@RequestBody Blog blog)
	{
		blogdao.createBlog(blog);
	}
	
	

	@RequestMapping(value="/getUsers",headers="accept=Application/json",method=RequestMethod.GET)
	public List <Users> getUsers()
	{
		List<Users> users=usersdao.listUsers();
return users;
}
	
	
	@RequestMapping(value="/deleteBlog",headers="accept=Application/json",method=RequestMethod.POST)
	public void deleteBlog(@RequestBody Blog blog){
		blogdao.deleteBlog(blog);
	}
	
	@RequestMapping(value="/updateBlogs",headers="accept=Application/json",method=RequestMethod.PUT)
	public void updateBlogs (@RequestBody Blog blog){
		blogdao.updateBlogs(blog);
	}
	
	
	
	
	@RequestMapping(value="/authenticate", method=RequestMethod.POST,headers="Accept=application/json")
	 public int authenticateUser(@RequestBody Users users)
	 {
		 System.out.println("email:"+users.getEmail());
		 System.out.println("password:"+users.getPassword());
	int result=0;
		 result=usersdao.validateUser(users.getEmail(),users.getPassword());
		 System.out.println("result we have got is:"+result);
		 return result;
	 }

	}
