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

    private GFElement x = new GFElement();
    private GFElement y = new GFElement();
    private GFElement z = new GFElement();

    public GFElement getX() {
        return x;
    }

    public GFElement getY() {
        return y;
    }

    public GFElement getZ() {
        return z;
    }

    public boolean isZero() {
        if (!x.isZero()) return false;
        if (!y.isZero()) return false;
        if (!z.isZero()) return false;
        return true;
    }
}
