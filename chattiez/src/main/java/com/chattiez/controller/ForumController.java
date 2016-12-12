package com.chattiez.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.chattiez.dao.ForumDao;
import com.chattiez.model.Forum;

@RestController
public class ForumController {
@Autowired
ForumDao forumdao;
	
@RequestMapping(value="/viewForums/{posted_By}",headers="accept=Application/json",method=RequestMethod.GET)
public List<Forum> viewForums(@PathVariable("posted_By") String posted_By)
{
	System.out.println("given name:"+posted_By);
	List<Forum> forums= forumdao.viewMyForums(posted_By);
	return forums;
}

@RequestMapping(value="/viewAllForums",headers="accept=Application/json",method=RequestMethod.GET)
public List<Forum> viewAllForums()
{
	List<Forum> forums=forumdao.viewForums();
	return forums;
}


@RequestMapping(value="/createForum",headers="accept=Application/json",method=RequestMethod.POST)
public void saveForum(@RequestBody Forum forum)
{
	forumdao.createForum(forum);
}


	
}
