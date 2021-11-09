/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DaoImplements;

import context.DBContext;
import dao.PostDao;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import models.Post;

/**
 *
 * @author Administrator
 */
public class PostDaoImp extends DBContext implements PostDao{

    

    /**
     *used to add a new post into Post table in database
     *
     * @param (post) a post object of Post.java entity
     */
    @Override
    public void addPost(Post post) {
        Date now = new Date(post.getPublicAt().getTime());// get current date as postdate

        try {
            String sql = "insert into Post values(?,?,?,?)";
            
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, post.getTitle());
            ps.setString(2, post.getImage());
            ps.setString(3, post.getContent());
            ps.setDate(4, now);
            ps.executeUpdate(); //update to database
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    
    /**
     *used to get a specific post form Post table in database
     *
     * @param (id) id code of the post, int
     * @return (Post) a post object of Post.java entity
     */
    @Override
    public Post getPostById(int id) {
        try {
            String query = "select * from Post where ID = ?";

            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Post p = new Post(
                        rs.getInt("ID"),
                        rs.getString("Title"),
                        rs.getString("Image"),
                        rs.getString("Content"),
                        rs.getDate("publicAt"));
                return p;
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }
    
    /**
     *used to get the total number of posts with similar title form Post table in database
     *
     * @param (title) a part of the post title used for searching leave blank if want to get all post, string
     * @return (int) total number of posts with similar title
     */
    @Override
    public int countPost(String title) {
        int count = 0;
        try {
            String query = "select count(*) from Post where Title like ?";

            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, "%" + title + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) { //count post
                count = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return count;

    }
    /**
     *used to get a page posts with similar title form Post table in database
     *
     * @param (pageIndex) number of page to get from database
     * @param (title) a part of the post title used for searching leave blank if want to get all post, string 
     * @return (List<Post>) page wanted to get
     */
    @Override
    public List<Post> getListPosts(int pageIndex, String title) {
        List<Post> list = new ArrayList<>();
        try {
            String query = "select * from("
                    + "select ROW_NUMBER() over (order by publicAt desc) as rn, *\n"
                    + "from Post where Title like ?"
                    + ")as x\n"
                    + "where rn between (?-1)*?+1"
                    + "and ?*?";
            /*
            * getting by pages of 6 posts each
            */
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, "%" + title + "%");
            ps.setInt(2, pageIndex);
            ps.setInt(3, 6);
            ps.setInt(4, pageIndex);
            ps.setInt(5, 6);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Post p = new Post(
                        rs.getInt("ID"),
                        rs.getString("Title"),
                        rs.getString("Image"),
                        rs.getString("Content"),
                        rs.getDate("publicAt")
                );
                list.add(p); //add to return result list
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    /**
     * used to delete a specific post form table Post in database
     *
     * @param (id) id code of deleting post
     */
    @Override
    public void deletePostById(int id) {
        try {
            String sql = "DELETE FROM [dbo].[Post] WHERE ID = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
