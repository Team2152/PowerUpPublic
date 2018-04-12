package org.usfirst.frc.team319.paths;

import org.usfirst.frc.team319.models.SrxMotionProfile;
import org.usfirst.frc.team319.models.SrxTrajectory;

public class FiveFeet extends SrxTrajectory{
	
	// WAYPOINTS:
	// (X,Y,degrees)
	// (0.00,0.00,0.00)
	// (3.00,0.00,0.00)
	
	public FiveFeet() {
		this(false);
	}
			
    public FiveFeet(boolean flipped) {
		super();
		
		double[][] leftPoints = {
				{0.173,3.451,10.000},
				{0.863,6.902,10.000},
				{2.416,15.531,10.000},
				{5.177,27.610,10.000},
				{9.491,43.140,10.000},
				{15.703,62.122,10.000},
				{24.159,84.555,10.000},
				{35.203,110.439,10.000},
				{49.180,139.775,10.000},
				{66.436,172.561,10.000},
				{87.316,208.799,10.000},
				{112.165,248.488,10.000},
				{141.328,291.629,10.000},
				{175.150,338.220,10.000},
				{213.976,388.263,10.000},
				{258.152,441.757,10.000},
				{308.022,498.702,10.000},
				{363.759,557.373,10.000},
				{425.364,616.044,10.000},
				{492.835,674.715,10.000},
				{566.174,733.386,10.000},
				{645.380,792.057,10.000},
				{730.452,850.728,10.000},
				{821.392,909.399,10.000},
				{918.199,968.069,10.000},
				{1020.701,1025.015,10.000},
				{1128.552,1078.509,10.000},
				{1241.407,1128.552,10.000},
				{1358.921,1175.143,10.000},
				{1480.749,1218.284,10.000},
				{1606.547,1257.973,10.000},
				{1735.968,1294.211,10.000},
				{1868.667,1326.997,10.000},
				{2004.301,1356.333,10.000},
				{2142.522,1382.217,10.000},
				{2282.987,1404.650,10.000},
				{2425.351,1423.632,10.000},
				{2569.267,1439.162,10.000},
				{2714.391,1451.241,10.000},
				{2860.378,1459.869,10.000},
				{3006.883,1465.046,10.000},
				{3153.560,1466.772,10.000},
				{3300.237,1466.772,10.000},
				{3446.914,1466.772,10.000},
				{3593.591,1466.772,10.000},
				{3740.268,1466.772,10.000},
				{3886.946,1466.772,10.000},
				{4033.623,1466.772,10.000},
				{4180.300,1466.772,10.000},
				{4326.977,1466.772,10.000},
				{4473.654,1466.772,10.000},
				{4620.332,1466.772,10.000},
				{4767.009,1466.772,10.000},
				{4913.686,1466.772,10.000},
				{5060.363,1466.772,10.000},
				{5207.040,1466.772,10.000},
				{5353.718,1466.772,10.000},
				{5500.395,1466.772,10.000},
				{5647.072,1466.772,10.000},
				{5793.749,1466.772,10.000},
				{5940.426,1466.772,10.000},
				{6087.104,1466.772,10.000},
				{6233.781,1466.772,10.000},
				{6380.458,1466.772,10.000},
				{6527.135,1466.772,10.000},
				{6673.812,1466.772,10.000},
				{6820.490,1466.772,10.000},
				{6967.167,1466.772,10.000},
				{7113.844,1466.772,10.000},
				{7260.521,1466.772,10.000},
				{7407.198,1466.772,10.000},
				{7553.876,1466.772,10.000},
				{7700.553,1466.772,10.000},
				{7847.230,1466.772,10.000},
				{7993.907,1466.772,10.000},
				{8140.584,1466.772,10.000},
				{8287.262,1466.772,10.000},
				{8433.939,1466.772,10.000},
				{8580.616,1466.772,10.000},
				{8727.293,1466.772,10.000},
				{8873.970,1466.772,10.000},
				{9020.648,1466.772,10.000},
				{9167.325,1466.772,10.000},
				{9314.002,1466.772,10.000},
				{9460.679,1466.772,10.000},
				{9607.356,1466.772,10.000},
				{9754.034,1466.772,10.000},
				{9900.711,1466.772,10.000},
				{10047.388,1466.772,10.000},
				{10194.065,1466.772,10.000},
				{10340.742,1466.772,10.000},
				{10487.419,1466.772,10.000},
				{10634.097,1466.772,10.000},
				{10780.774,1466.772,10.000},
				{10927.451,1466.772,10.000},
				{11074.128,1466.772,10.000},
				{11220.805,1466.772,10.000},
				{11367.483,1466.772,10.000},
				{11514.160,1466.772,10.000},
				{11660.837,1466.772,10.000},
				{11807.514,1466.772,10.000},
				{11954.191,1466.772,10.000},
				{12100.869,1466.772,10.000},
				{12247.546,1466.772,10.000},
				{12394.223,1466.772,10.000},
				{12540.900,1466.772,10.000},
				{12687.577,1466.772,10.000},
				{12834.255,1466.772,10.000},
				{12980.932,1466.772,10.000},
				{13127.609,1466.772,10.000},
				{13274.286,1466.772,10.000},
				{13420.963,1466.772,10.000},
				{13567.641,1466.772,10.000},
				{13714.318,1466.772,10.000},
				{13860.995,1466.772,10.000},
				{14007.672,1466.772,10.000},
				{14154.349,1466.772,10.000},
				{14301.027,1466.772,10.000},
				{14447.704,1466.772,10.000},
				{14594.381,1466.772,10.000},
				{14740.886,1465.046,10.000},
				{14886.873,1459.869,10.000},
				{15031.997,1451.241,10.000},
				{15175.913,1439.162,10.000},
				{15318.276,1423.632,10.000},
				{15458.741,1404.650,10.000},
				{15596.963,1382.217,10.000},
				{15732.596,1356.333,10.000},
				{15865.296,1326.997,10.000},
				{15994.717,1294.211,10.000},
				{16120.514,1257.973,10.000},
				{16242.342,1218.284,10.000},
				{16359.857,1175.143,10.000},
				{16472.712,1128.552,10.000},
				{16580.563,1078.509,10.000},
				{16683.064,1025.015,10.000},
				{16779.871,968.069,10.000},
				{16870.811,909.399,10.000},
				{16955.884,850.728,10.000},
				{17035.089,792.057,10.000},
				{17108.428,733.386,10.000},
				{17175.900,674.715,10.000},
				{17237.504,616.044,10.000},
				{17293.241,557.373,10.000},
				{17343.112,498.702,10.000},
				{17387.287,441.757,10.000},
				{17426.114,388.263,10.000},
				{17459.936,338.220,10.000},
				{17489.099,291.629,10.000},
				{17513.947,248.488,10.000},
				{17534.827,208.799,10.000},
				{17552.083,172.561,10.000},
				{17566.061,139.775,10.000},
				{17577.105,110.439,10.000},
				{17585.560,84.555,10.000},
				{17591.773,62.122,10.000},
				{17596.087,43.140,10.000},
				{17598.848,27.610,10.000},
				{17600.401,15.531,10.000},
				{17601.091,6.902,10.000},
				{17601.263,1.726,10.000},
				{17601.263,0.000,10.000}
		};
		
		double[][] rightPoints = {
				{0.173,3.451,10.000},
				{0.863,6.902,10.000},
				{2.416,15.531,10.000},
				{5.177,27.610,10.000},
				{9.491,43.140,10.000},
				{15.703,62.122,10.000},
				{24.159,84.555,10.000},
				{35.203,110.439,10.000},
				{49.180,139.775,10.000},
				{66.436,172.561,10.000},
				{87.316,208.799,10.000},
				{112.165,248.488,10.000},
				{141.328,291.629,10.000},
				{175.150,338.220,10.000},
				{213.976,388.263,10.000},
				{258.152,441.757,10.000},
				{308.022,498.702,10.000},
				{363.759,557.373,10.000},
				{425.364,616.044,10.000},
				{492.835,674.715,10.000},
				{566.174,733.386,10.000},
				{645.380,792.057,10.000},
				{730.452,850.728,10.000},
				{821.392,909.399,10.000},
				{918.199,968.069,10.000},
				{1020.701,1025.015,10.000},
				{1128.552,1078.509,10.000},
				{1241.407,1128.552,10.000},
				{1358.921,1175.143,10.000},
				{1480.749,1218.284,10.000},
				{1606.547,1257.973,10.000},
				{1735.968,1294.211,10.000},
				{1868.667,1326.997,10.000},
				{2004.301,1356.333,10.000},
				{2142.522,1382.217,10.000},
				{2282.987,1404.650,10.000},
				{2425.351,1423.632,10.000},
				{2569.267,1439.162,10.000},
				{2714.391,1451.241,10.000},
				{2860.378,1459.869,10.000},
				{3006.883,1465.046,10.000},
				{3153.560,1466.772,10.000},
				{3300.237,1466.772,10.000},
				{3446.914,1466.772,10.000},
				{3593.591,1466.772,10.000},
				{3740.268,1466.772,10.000},
				{3886.946,1466.772,10.000},
				{4033.623,1466.772,10.000},
				{4180.300,1466.772,10.000},
				{4326.977,1466.772,10.000},
				{4473.654,1466.772,10.000},
				{4620.332,1466.772,10.000},
				{4767.009,1466.772,10.000},
				{4913.686,1466.772,10.000},
				{5060.363,1466.772,10.000},
				{5207.040,1466.772,10.000},
				{5353.718,1466.772,10.000},
				{5500.395,1466.772,10.000},
				{5647.072,1466.772,10.000},
				{5793.749,1466.772,10.000},
				{5940.426,1466.772,10.000},
				{6087.104,1466.772,10.000},
				{6233.781,1466.772,10.000},
				{6380.458,1466.772,10.000},
				{6527.135,1466.772,10.000},
				{6673.812,1466.772,10.000},
				{6820.490,1466.772,10.000},
				{6967.167,1466.772,10.000},
				{7113.844,1466.772,10.000},
				{7260.521,1466.772,10.000},
				{7407.198,1466.772,10.000},
				{7553.876,1466.772,10.000},
				{7700.553,1466.772,10.000},
				{7847.230,1466.772,10.000},
				{7993.907,1466.772,10.000},
				{8140.584,1466.772,10.000},
				{8287.262,1466.772,10.000},
				{8433.939,1466.772,10.000},
				{8580.616,1466.772,10.000},
				{8727.293,1466.772,10.000},
				{8873.970,1466.772,10.000},
				{9020.648,1466.772,10.000},
				{9167.325,1466.772,10.000},
				{9314.002,1466.772,10.000},
				{9460.679,1466.772,10.000},
				{9607.356,1466.772,10.000},
				{9754.034,1466.772,10.000},
				{9900.711,1466.772,10.000},
				{10047.388,1466.772,10.000},
				{10194.065,1466.772,10.000},
				{10340.742,1466.772,10.000},
				{10487.419,1466.772,10.000},
				{10634.097,1466.772,10.000},
				{10780.774,1466.772,10.000},
				{10927.451,1466.772,10.000},
				{11074.128,1466.772,10.000},
				{11220.805,1466.772,10.000},
				{11367.483,1466.772,10.000},
				{11514.160,1466.772,10.000},
				{11660.837,1466.772,10.000},
				{11807.514,1466.772,10.000},
				{11954.191,1466.772,10.000},
				{12100.869,1466.772,10.000},
				{12247.546,1466.772,10.000},
				{12394.223,1466.772,10.000},
				{12540.900,1466.772,10.000},
				{12687.577,1466.772,10.000},
				{12834.255,1466.772,10.000},
				{12980.932,1466.772,10.000},
				{13127.609,1466.772,10.000},
				{13274.286,1466.772,10.000},
				{13420.963,1466.772,10.000},
				{13567.641,1466.772,10.000},
				{13714.318,1466.772,10.000},
				{13860.995,1466.772,10.000},
				{14007.672,1466.772,10.000},
				{14154.349,1466.772,10.000},
				{14301.027,1466.772,10.000},
				{14447.704,1466.772,10.000},
				{14594.381,1466.772,10.000},
				{14740.886,1465.046,10.000},
				{14886.873,1459.869,10.000},
				{15031.997,1451.241,10.000},
				{15175.913,1439.162,10.000},
				{15318.276,1423.632,10.000},
				{15458.741,1404.650,10.000},
				{15596.963,1382.217,10.000},
				{15732.596,1356.333,10.000},
				{15865.296,1326.997,10.000},
				{15994.717,1294.211,10.000},
				{16120.514,1257.973,10.000},
				{16242.342,1218.284,10.000},
				{16359.857,1175.143,10.000},
				{16472.712,1128.552,10.000},
				{16580.563,1078.509,10.000},
				{16683.064,1025.015,10.000},
				{16779.871,968.069,10.000},
				{16870.811,909.399,10.000},
				{16955.884,850.728,10.000},
				{17035.089,792.057,10.000},
				{17108.428,733.386,10.000},
				{17175.900,674.715,10.000},
				{17237.504,616.044,10.000},
				{17293.241,557.373,10.000},
				{17343.112,498.702,10.000},
				{17387.287,441.757,10.000},
				{17426.114,388.263,10.000},
				{17459.936,338.220,10.000},
				{17489.099,291.629,10.000},
				{17513.947,248.488,10.000},
				{17534.827,208.799,10.000},
				{17552.083,172.561,10.000},
				{17566.061,139.775,10.000},
				{17577.105,110.439,10.000},
				{17585.560,84.555,10.000},
				{17591.773,62.122,10.000},
				{17596.087,43.140,10.000},
				{17598.848,27.610,10.000},
				{17600.401,15.531,10.000},
				{17601.091,6.902,10.000},
				{17601.263,1.726,10.000},
				{17601.263,0.000,10.000}
		};
		
		if (flipped) {
			rightProfile = new SrxMotionProfile(leftPoints.length, leftPoints);
			leftProfile = new SrxMotionProfile(rightPoints.length, rightPoints);
		} else {
			leftProfile = new SrxMotionProfile(leftPoints.length, leftPoints);
			rightProfile = new SrxMotionProfile(rightPoints.length, rightPoints);
		}
	}

}