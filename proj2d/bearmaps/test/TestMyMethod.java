package bearmaps.test;

import bearmaps.proj2d.utils.Constants;

public class TestMyMethod {

    public static int[] rasterIndex (){
//        double target_lrlon=-122.2104604264636;
//        double target_ullon=-122.30410170759153;
//        double w=1085.0;
//        double h=566.0;
//        double target_ullat=37.870213571328854;
//        double target_lrlat=37.8318576119893;



//        double target_lrlon=-122.20908713544797;
//        double target_ullon=-122.3027284165759;
//        double w=305.0;
//        double h=300.0;
//        double target_ullat=37.88708748276975;
//        double target_lrlat=37.848731523430196;

        double target_lrlon=-122.24053369025242;
        double target_ullon=-122.24163047377972;
        double w=892.0;
        double h=875.0;
        double target_ullat=37.87655856892288;
        double target_lrlat=37.87548268822065;

        int depth = 0;
        //calculate LonDPP per depth:
        for (int i = 0; i <= 7; i++){
            double lonDPP = Math.abs(Constants.ROOT_ULLON - Constants.ROOT_LRLON) / (Math.pow(2, i) * 256);
            double query_lonDPP = Math.abs(target_ullon - target_lrlon) / w;
            if (lonDPP < query_lonDPP){
                depth = i;
                break;
            }
            depth = i;
        }
        double y_tile = Math.abs(Constants.ROOT_ULLAT - Constants.ROOT_LRLAT) / Math.pow(2, depth);
        double x_tile = Math.abs(Constants.ROOT_ULLON - Constants.ROOT_LRLON) / Math.pow(2, depth);
        int xMin, yMin, xMax, yMax;
        if (target_lrlon > Constants.ROOT_LRLON){ xMin = 0; }
        else{ xMin = (int) (Math.floor(((Math.abs(target_ullon - Constants.ROOT_ULLON))/x_tile))); }
        if (target_lrlat < Constants.ROOT_LRLAT){ yMin = 0; }
        else {yMin = (int) (Math.floor(((Math.abs(target_ullat - Constants.ROOT_ULLAT))/y_tile))); }
        if (target_ullon < Constants.ROOT_ULLON) { xMax = (int) (Math.pow(2, depth)) - 1; }
        else {xMax = (int) (Math.floor(((Math.abs(target_lrlon - Constants.ROOT_ULLON))/x_tile))); }
        if (target_ullat > Constants.ROOT_ULLAT) { yMax = (int) (Math.pow(2, depth)) - 1; }
        else {yMax = (int) (Math.floor(((Math.abs(target_lrlat - Constants.ROOT_ULLAT))/y_tile))); }
        return new int[]{xMin, yMin, xMax, yMax};
    }


    public static void main(String[] args) {
        int[] index = rasterIndex();
        String[][] files = new String[index[3] - index[1] + 1][index[2] - index[0] + 1];
        int m = 0;
        for (int i = index[1]; i <= index[3]; i++){
            int n = 0;
            for (int j = index[0]; j <= index[2]; j++){
                files[m][n] = String.format("d2_x%s_y%s.png", j, i);
                System.out.print(files[m][n]);
                System.out.print("    ");
                n++;
            }
            m++;
            System.out.println();
        }

    }
}
