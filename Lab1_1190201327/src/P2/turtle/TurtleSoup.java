/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P2.turtle;

import java.util.List;
import java.util.Set;
import java.util.ArrayList;
import java.util.HashSet;

public class TurtleSoup {

    /**
     * Draw a square.
     * 
     * @param turtle the turtle context
     * @param sideLength length of each side
     */
    public static void drawSquare(Turtle turtle, int sideLength) {
        //throw new RuntimeException("implement me!");
        int i = 0;
        for(i = 0; i < 4; i++)
        {
        	turtle.forward(100);
        	turtle.turn(90);
        }
    }

    /**
     * Determine inside angles of a regular polygon.
     * 
     * There is a simple formula for calculating the inside angles of a polygon;
     * you should derive it and use it here.
     * 
     * @param sides number of sides, where sides must be > 2
     * @return angle in degrees, where 0 <= angle < 360
     */
    public static double calculateRegularPolygonAngle(int sides) {
        //throw new RuntimeException("implement me!");
    	double angle = (sides - 2) * 180.0 / sides;
    	return angle;
    }

    /**
     * Determine number of sides given the size of interior angles of a regular polygon.
     * 
     * There is a simple formula for this; you should derive it and use it here.
     * Make sure you *properly round* the answer before you return it (see java.lang.Math).
     * HINT: it is easier if you think about the exterior angles.
     * 
     * @param angle size of interior angles in degrees, where 0 < angle < 180
     * @return the integer number of sides
     */
    public static int calculatePolygonSidesFromAngle(double angle) {
    	try {
    		return (int)Math.round((360.0 / (180.0 - angle)));
    	}catch(Exception e) {
    		throw new RuntimeException("implement me!");
    	}
    }

    /**
     * Given the number of sides, draw a regular polygon.
     * 
     * (0,0) is the lower-left corner of the polygon; use only right-hand turns to draw.
     * 
     * @param turtle the turtle context
     * @param sides number of sides of the polygon to draw
     * @param sideLength length of each side
     */
    public static void drawRegularPolygon(Turtle turtle, int sides, int sideLength) {
        //throw new RuntimeException("implement me!");
    	int i;
    	double angle; 
    	angle = calculateRegularPolygonAngle(sides);
    	for(i = 0; i < sides; i++)
    	{
    		turtle.forward(sideLength);
    		turtle.turn(180 - angle);
    	}
    }

    /**
     * Given the current direction, current location, and a target location, calculate the Bearing
     * towards the target point.
     * 
     * The return value is the angle input to turn() that would point the turtle in the direction of
     * the target point (targetX,targetY), given that the turtle is already at the point
     * (currentX,currentY) and is facing at angle currentBearing. The angle must be expressed in
     * degrees, where 0 <= angle < 360. 
     *
     * HINT: look at http://en.wikipedia.org/wiki/Atan2 and Java's math libraries
     * 
     * @param currentBearing current direction as clockwise from north
     * @param currentX current location x-coordinate
     * @param currentY current location y-coordinate
     * @param targetX target point x-coordinate
     * @param targetY target point y-coordinate
     * @return adjustment to Bearing (right turn amount) to get to target point,
     *         must be 0 <= angle < 360
     */
    public static double calculateBearingToPoint(double currentBearing, int currentX, int currentY,
                                                 int targetX, int targetY) {
    	try {
    		double change = 0.0;
    		if(currentY == targetY)
    		{
    			if(currentX < targetX) change = 90.0;
    			else if(currentX > targetX) change = 270.0;
    			else change = 0.0;
    		}
    		else
    		{
    			if(currentX == targetX)
    			{
    				if(currentY < targetY) change = 0.0;
    				else change = 180.0;
    			}
    			if(currentX < targetX)
    			{
					change = Math.toDegrees(Math.atan((double)(targetX - currentX) / (double)(targetY - currentY)));
    				if(currentY < targetY)
    				{
    					change = change;
    				}
    				else {
    					change = 180.0 + change;
    				}
    			}
    			if(currentX > targetX)
    			{
					change = Math.toDegrees(Math.atan((double)((2 * currentX - targetX) - currentX) / (double)(targetY - currentY)));
					if(currentY < targetY)
    				{
    					change = 360.0 - change;
    				}
    				else {
    					change = 360.0 - (180 + change);
    				}
    			}
    		}
    		if(currentBearing < change) return change - currentBearing;
    		else if(currentBearing > change) return 360.0 - (currentBearing - change);
    		else return 0.0;
    	}catch(Exception e) {
    		throw new RuntimeException("implement me!");
    	}
    }

    /**
     * Given a sequence of points, calculate the Bearing adjustments needed to get from each point
     * to the next.
     * 
     * Assumes that the turtle starts at the first point given, facing up (i.e. 0 degrees).
     * For each subsequent point, assumes that the turtle is still facing in the direction it was
     * facing when it moved to the previous point.
     * You should use calculateBearingToPoint() to implement this function.
     * 
     * @param xCoords list of x-coordinates (must be same length as yCoords)
     * @param yCoords list of y-coordinates (must be same length as xCoords)
     * @return list of Bearing adjustments between points, of size 0 if (# of points) == 0,
     *         otherwise of size (# of points) - 1
     */
    public static List<Double> calculateBearings(List<Integer> xCoords, List<Integer> yCoords) {
        try {
        	int i;
        	List<Double> angles = new ArrayList<Double>();
        	double current = 0.0;
        	for(i = 0; i < xCoords.size() - 1; i++)
        	{
        		angles.add(calculateBearingToPoint(current, xCoords.get(i), yCoords.get(i), xCoords.get(i + 1), yCoords.get(i + 1)));
        		current = (current + angles.get(i)) % 360;
        	}
        	return angles;
        }catch(Exception e) {
        	throw new RuntimeException("implement me!");
        }
    }
    
    /**
     * Given a set of points, compute the convex hull, the smallest convex set that contains all the points 
     * in a set of input points. The gift-wrapping algorithm is one simple approach to this problem, and 
     * there are other algorithms too.
     * 
     * @param points a set of points with xCoords and yCoords. It might be empty, contain only 1 point, two points or more.
     * @return minimal subset of the input points that form the vertices of the perimeter of the convex hull
     */
    public static Set<Point> convexHull(Set<Point> points) {
    	try {
    		if(points.size() <= 3) return points;
    		Set<Point> Hullp = new HashSet<Point>();
    		Point [] P = points.toArray(new Point[points.size()]);
    		int [] in = new int[points.size()];
    		int i, startindex = 0, nextindex = 0, endindex;
    		double mangle, nowangle;
    		Point start, next = P[0], end;
    		start = P[0];
    		for(i = 1; i < points.size(); i++)//找最左下角的点
    		{
    			if(P[i].x() < start.x()) {
    				start = P[i];
    				startindex = i;
    			}
    			else if(P[i].x() == start.x())
    			{
    				if(P[i].y() < start.y())
    				{
    					start = P[i];
        				startindex = i;
    				}
    			}
    		}
    		end = start;
    		endindex = startindex;
    		do {
    			mangle = 360.0;
    			for(i = 0; i < points.size(); i++)
    			{
    				if(i != startindex && in[i] == 0)
    				{	
    					nowangle = calculateBearingToPoint(0.0, (int)P[startindex].x(), (int)P[startindex].y(), (int)P[i].x(), (int)P[i].y());
    					if(nowangle < mangle)//第一遍必然会进来的 
    					{
    						mangle = nowangle;
    						nextindex = i;
   							next = P[i];
   						}
   						else if(nowangle == mangle) 
   						{
 
    						if((P[i].x() - P[startindex].x()) * (P[i].x() - P[nextindex].x()) +  (P[i].y() - P[startindex].y()) * (P[i].y() - P[nextindex].y())> 0)
    						{
    							in[nextindex] = 1;
    							nextindex = i;
    							next = P[i];
    						}
    						else if((P[i].x() - P[startindex].x()) * (P[i].x() - P[nextindex].x()) + (P[i].y() - P[startindex].y()) * (P[i].y() - P[nextindex].y())< 0)
    						{
    							in[i] = 1;
    						}
    					}
    				}	
    			}
    			in[nextindex] = 1;
    			Hullp.add(next);
    			startindex = nextindex;
    			start = next;
    		}while(endindex != startindex);
    		Hullp.add(end);
    		return Hullp;
    	}catch(Exception e) {
            throw new RuntimeException("implement me!");
    	}
    }
    
    /**
     * Draw your personal, custom art.
     * 
     * Many interesting images can be drawn using the simple implementation of a turtle.  For this
     * function, draw something interesting; the complexity can be as little or as much as you want.
     * 
     * @param turtle the turtle context
     */
    public static void drawPersonalArt(Turtle turtle) {
        try {
        	int i;
        	for(i = 0; i < 3600; i++) {
        		turtle.color(PenColor.PINK);
        		turtle.forward(4);
        		turtle.turn((2 + i / 100) % 360);
        	}
        }catch(Exception e) {
        	throw new RuntimeException("implement me!");
        }
    }

    /**
     * Main method.
     * 
     * This is the method that runs when you run "java TurtleSoup".
     * 
     * @param args unused
     */
    public static void main(String args[]) {
    	
    	DrawableTurtle turtle = new DrawableTurtle();
    	
        drawSquare(turtle, 40);
        
    	//System.out.println(calculateRegularPolygonAngle(4));
        
    	/*
    	drawRegularPolygon(turtle, 8, 50);
    	turtle.draw();
    	*
    	*/
    	
    	/*
    	System.out.println(calculateBearingToPoint(190.0, 0, 0, -1, 1));
    	List<Integer> xCoords = new ArrayList<Integer>();
    	xCoords.add(0, 0);
    	xCoords.add(1, 0);
    	xCoords.add(2, 1);
    	xCoords.add(3, 1);
    	List<Integer> yCoords = new ArrayList<Integer>();
    	yCoords.add(0, 1);
    	yCoords.add(1, 0);
    	yCoords.add(2, 0);
    	yCoords.add(3, 1);
    	
    	calculateBearings(xCoords, yCoords);
    	*/
        
    	//drawPersonalArt(turtle);
        
     // draw the window
        turtle.draw();
    }

}
