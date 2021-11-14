/*
 * Copyright(C) 2021,  FPT.
 *  LTS:
 *  LaptopShop
 *
 * Record of change:
 * DATE                       Version             AUTHOR                       DESCRIPTION
 * 2021/11/15                   1.0                LongCH                       create file
 */
package DaoImplements;

import context.DBContext;
import dao.FeedBackDao;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import models.Feedback;

/**
 * The class contains method find update, delete, insert product from product
 * and productinfo table indatabase.
 *
 *
 * @author Chu Hoang Long
 */
public class FeedBackDaoImpl extends DBContext implements FeedBackDao {

    @Override
    public void deleteFeedback(int id) {
        try {
            String sql = "delete from [dbo].[Feedback] where ID = ?";

            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();
        } catch (Exception e) {
        }
    }

    @Override
    public void addFeedback(Feedback feedback) {
        java.util.Date utilDate = new java.util.Date();
        Date now = new Date(utilDate.getTime());

        try {
            String sql = "insert into Feedback values(?,?,?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, feedback.getUsername());
            ps.setString(2, feedback.getMessage());
            ps.setDate(3, now);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    @Override
    public Feedback getFeedbackById(int id) {
        try {
            String sql = "select * from Feedback where ID = ? ";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Feedback f = new Feedback(
                        rs.getInt("ID"),
                        rs.getString("username"),
                        rs.getString("message"),
                        rs.getDate("createAt")
                );

                return f;
            }
        } catch (Exception e) {
        }
        return null;
    }

    @Override
    public ArrayList<Feedback> getFeedbacks(int pageIndex) {
        ArrayList<Feedback> list = new ArrayList<>();
        try {
            String query = "select * from("
                    + "select ROW_NUMBER() over (order by ID desc) as rn, *\n"
                    + "from Feedback"
                    + ")as x\n"
                    + "where rn between (?-1)*?+1"
                    + "and ?*?";

            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, pageIndex);
            ps.setInt(2, 6);
            ps.setInt(3, pageIndex);
            ps.setInt(4, 6);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Feedback f = new Feedback(rs.getInt("ID"),
                        rs.getString("username"), rs.getString("message"),
                        rs.getDate("createAt"));
                list.add(f);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public int countFeedback() {
        int count = 0;
        try {
            String query = "select count(*) from Feedback";

            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return count;
    }
}
