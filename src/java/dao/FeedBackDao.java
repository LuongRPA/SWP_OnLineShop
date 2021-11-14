/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.ArrayList;
import models.Feedback;

/**
 *
 * @author Dell
 */
public interface FeedBackDao {

    public void deleteFeedback(int id);

    public void addFeedback(Feedback feedback);

    public Feedback getFeedbackById(int id);

    public ArrayList<Feedback> getFeedbacks(int pageIndex);

    public int countFeedback();
}
