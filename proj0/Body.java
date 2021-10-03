
public class Body {
    public double xxPos; 
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    public Body(double xP, double yP, double xV,double yV, double m, String img){
        xxPos = xP; 
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }
    public Body(Body b){
        xxPos = b.xxPos;
        yyPos = b.yyPos;
        xxVel = b.xxVel;
        yyVel = b.yyVel;
        mass = b.mass;
        imgFileName = b.imgFileName;
    }
    public double calcDistance(Body target){
        double distance;
        double y1 = this.yyPos;
        double x1 = this.xxPos;
        double y2 = target.yyPos;
        double x2 = target.xxPos;
        distance = Math.sqrt((y2 - y1) * (y2 - y1) + (x2 - x1) * (x2 - x1));
        return distance;
    }
    public double calcForceExertedBy(Body target){
        double force;
        final double gravityConstant = 6.67e-11;
        if (this.equals(target)){
            force = 0.0;
        }else{
            double distance = this.calcDistance(target);
            double m1 = this.mass;
            double m2 = target.mass;
            force = gravityConstant*m1*m2/(distance*distance);
        }
        return force;
    }
    public double calcForceExertedByX(Body target){
        double force = this.calcForceExertedBy(target);
        double distance = this.calcDistance(target);
        double x = target.xxPos - this.xxPos;
        return force*x/distance;
    }
    public double calcForceExertedByY(Body target){
        double force = this.calcForceExertedBy(target);
        double distance = this.calcDistance(target);
        double y = target.yyPos - this.yyPos;
        return force*y/distance;
    }
    public double calcNetForceExertedByX(Body targets[]){
        double netForce = 0.0;
        for (Body target : targets){
            if (this.equals(target)){
                continue;
            }else{
                netForce += this.calcForceExertedByX(target);
            }
        }
        return netForce;
    }
    public double calcNetForceExertedByY(Body targets[]){
        double netForce = 0.0;
        for (Body target : targets){
            if (this.equals(target)){
                continue;
            }else{
                netForce += this.calcForceExertedByY(target);
            }
        }
        return netForce;
    }

    public void update(double dt, double fX, double fY){
        double m = this.mass;
        double aX = fX/m;
        double aY = fY/m;
        this.xxVel = this.xxVel + dt*aX; 
        this.yyVel = this.yyVel + dt*aY;
        this.xxPos = this.xxPos + dt*this.xxVel;
        this.yyPos = this.yyPos + dt*this.yyVel;
    }

    public void draw(){
        StdDraw.picture(this.xxPos, this.yyPos, "images/" + this.imgFileName);
    }

}