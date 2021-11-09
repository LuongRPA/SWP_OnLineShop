/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import models.Post;

/**
 *
 * @author Administrator
 */
public interface PostDao{
    public void addPost(Post post);
    public Post getPostById(int id);
    public int countPost(String title);
    public List<Post> getListPosts(int pageIndex, String title);
    public void deletePostById(int id);
}
