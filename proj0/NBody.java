public class NBody {
    public static String imageToDraw = "images/starfield.jpg";
    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        int number = readNumber(filename);
        double radius = readRadius(filename);
        Body[] bodies = readBodies(filename);
        StdDraw.enableDoubleBuffering();
        double time = 0.0;
        int waitTimeMilliseconds = 100;
        while (time <= T){
            time = time + dt;
            double[] xForces = new double [number];
            double[] yForces = new double [number];
            for (int i = 0; i < number; i++){
                xForces[i] = bodies[i].calcNetForceExertedByX(bodies);
                yForces[i] = bodies[i].calcNetForceExertedByY(bodies);
            } 
            for (int i = 0; i < number; i++){
                bodies[i].update(dt, xForces[i], yForces[i]);
            }
            StdDraw.setScale(-radius, radius);
            StdDraw.picture(0, 0, imageToDraw);
            for (Body body : bodies){
                body.draw();
            }
            StdDraw.show();
			StdDraw.pause(waitTimeMilliseconds);
        }
        
    }

    public static int readNumber(String fileName){
        In in = new In(fileName);
        int number = in.readInt();
        return number;
    }


    public static double readRadius(String fileName){
        In in = new In(fileName);
        in.readInt();
        double radius = in.readDouble();
        return radius;
    }

    public static Body[] readBodies(String fileName){
        In in = new In(fileName);
        in.readInt();
        in.readDouble();
        Body[] bodies = new Body[5];
        for (int i = 0; i < 5; i++) {
            double xpos = in.readDouble();
            double ypos = in.readDouble();
            double xvel = in.readDouble();
            double yvel = in.readDouble();
            double mass = in.readDouble();
            String image = in.readString();
            bodies[i] = new Body(xpos, ypos, xvel, yvel, mass, image);
        }
        return bodies;
    } 
}
