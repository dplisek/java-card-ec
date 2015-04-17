/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pack1;

/**
 *
 * @author Dominik
 */
public class ProjectivePoint {
    
    private GFMember x = new GFMember();
    private GFMember y = new GFMember();
    private GFMember z = new GFMember();

    public GFMember getX() {
        return x;
    }

    public GFMember getY() {
        return y;
    }

    public GFMember getZ() {
        return z;
    }

    public static ProjectivePoint createInfinity() {
        ProjectivePoint inf = new ProjectivePoint();
        inf.x = GFMember.createOne();
        inf.y = GFMember.createOne();
        return inf;
    }
}
