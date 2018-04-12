package org.usfirst.frc.team319.paths;

import com.team319.trajectory.SrxMotionProfile;
import com.team319.trajectory.SrxTrajectory;

public class CrossTheLine extends SrxTrajectory{
	
	// WAYPOINTS:
	// (X,Y,degrees)
	// (0.00,0.00,0.00)
	// (10.00,0.00,0.00)
	
	public CrossTheLine() {
		this(false);
	}
			
    public CrossTheLine(boolean flipped) {
		super();
		
		double[][] leftPoints = {
				{-0.502,-10.032,10.000},
				{-2.508,-20.063,10.000},
				{-7.022,-45.142,10.000},
				{-15.047,-80.253,10.000},
				{-27.587,-125.395,10.000},
				{-45.644,-180.568,10.000},
				{-70.221,-245.773,10.000},
				{-102.322,-321.010,10.000},
				{-142.950,-406.279,10.000},
				{-193.108,-501.578,10.000},
				{-253.799,-606.910,10.000},
				{-326.026,-722.273,10.000},
				{-410.793,-847.668,10.000},
				{-509.102,-983.094,10.000},
				{-621.957,-1128.552,10.000},
				{-750.361,-1284.041,10.000},
				{-895.318,-1449.562,10.000},
				{-1057.327,-1620.099,10.000},
				{-1236.391,-1790.635,10.000},
				{-1432.508,-1961.172,10.000},
				{-1645.679,-2131.709,10.000},
				{-1875.904,-2302.245,10.000},
				{-2123.182,-2472.782,10.000},
				{-2387.514,-2643.319,10.000},
				{-2668.899,-2813.855,10.000},
				{-2967.338,-2984.392,10.000},
				{-3282.831,-3154.929,10.000},
				{-3615.378,-3325.465,10.000},
				{-3964.978,-3496.002,10.000},
				{-4331.632,-3666.539,10.000},
				{-4715.339,-3837.075,10.000},
				{-5116.101,-4007.612,10.000},
				{-5533.915,-4178.149,10.000},
				{-5968.784,-4348.685,10.000},
				{-6420.706,-4519.222,10.000},
				{-6889.682,-4689.759,10.000},
				{-7375.712,-4860.296,10.000},
				{-7878.795,-5030.832,10.000},
				{-8398.932,-5201.369,10.000},
				{-8936.122,-5371.906,10.000},
				{-9489.865,-5537.427,10.000},
				{-10059.157,-5692.916,10.000},
				{-10642.994,-5838.374,10.000},
				{-11240.374,-5973.800,10.000},
				{-11850.293,-6099.194,10.000},
				{-12471.749,-6214.557,10.000},
				{-13103.738,-6319.889,10.000},
				{-13745.257,-6415.189,10.000},
				{-14395.303,-6500.457,10.000},
				{-15052.872,-6575.694,10.000},
				{-15716.962,-6640.899,10.000},
				{-16386.569,-6696.073,10.000},
				{-17060.691,-6741.215,10.000},
				{-17738.323,-6776.325,10.000},
				{-18418.464,-6801.404,10.000},
				{-19100.109,-6816.452,10.000},
				{-19782.256,-6821.467,10.000},
				{-20464.402,-6821.467,10.000},
				{-21146.549,-6821.467,10.000},
				{-21828.696,-6821.467,10.000},
				{-22510.843,-6821.467,10.000},
				{-23192.989,-6821.467,10.000},
				{-23875.136,-6821.467,10.000},
				{-24557.283,-6821.467,10.000},
				{-25239.430,-6821.467,10.000},
				{-25921.576,-6821.467,10.000},
				{-26603.723,-6821.467,10.000},
				{-27285.870,-6821.467,10.000},
				{-27968.017,-6821.467,10.000},
				{-28650.163,-6821.467,10.000},
				{-29332.310,-6821.467,10.000},
				{-30014.457,-6821.467,10.000},
				{-30696.603,-6821.467,10.000},
				{-31378.750,-6821.467,10.000},
				{-32060.897,-6821.467,10.000},
				{-32743.044,-6821.467,10.000},
				{-33425.190,-6821.467,10.000},
				{-34107.337,-6821.467,10.000},
				{-34789.484,-6821.467,10.000},
				{-35471.631,-6821.467,10.000},
				{-36153.777,-6821.467,10.000},
				{-36835.924,-6821.467,10.000},
				{-37518.071,-6821.467,10.000},
				{-38200.218,-6821.467,10.000},
				{-38882.364,-6821.467,10.000},
				{-39564.511,-6821.467,10.000},
				{-40246.658,-6821.467,10.000},
				{-40928.805,-6821.467,10.000},
				{-41610.951,-6821.467,10.000},
				{-42293.098,-6821.467,10.000},
				{-42975.245,-6821.467,10.000},
				{-43657.392,-6821.467,10.000},
				{-44339.538,-6821.467,10.000},
				{-45021.685,-6821.467,10.000},
				{-45703.832,-6821.467,10.000},
				{-46385.979,-6821.467,10.000},
				{-47068.125,-6821.467,10.000},
				{-47750.272,-6821.467,10.000},
				{-48432.419,-6821.467,10.000},
				{-49114.566,-6821.467,10.000},
				{-49796.712,-6821.467,10.000},
				{-50478.859,-6821.467,10.000},
				{-51161.006,-6821.467,10.000},
				{-51843.153,-6821.467,10.000},
				{-52525.299,-6821.467,10.000},
				{-53207.446,-6821.467,10.000},
				{-53889.593,-6821.467,10.000},
				{-54571.740,-6821.467,10.000},
				{-55253.886,-6821.467,10.000},
				{-55936.033,-6821.467,10.000},
				{-56618.180,-6821.467,10.000},
				{-57300.327,-6821.467,10.000},
				{-57982.473,-6821.467,10.000},
				{-58664.620,-6821.467,10.000},
				{-59346.767,-6821.467,10.000},
				{-60028.913,-6821.467,10.000},
				{-60711.060,-6821.467,10.000},
				{-61393.207,-6821.467,10.000},
				{-62075.354,-6821.467,10.000},
				{-62757.500,-6821.467,10.000},
				{-63439.647,-6821.467,10.000},
				{-64121.794,-6821.467,10.000},
				{-64803.941,-6821.467,10.000},
				{-65486.087,-6821.467,10.000},
				{-66168.234,-6821.467,10.000},
				{-66850.381,-6821.467,10.000},
				{-67532.528,-6821.467,10.000},
				{-68214.674,-6821.467,10.000},
				{-68896.821,-6821.467,10.000},
				{-69578.968,-6821.467,10.000},
				{-70261.115,-6821.467,10.000},
				{-70943.261,-6821.467,10.000},
				{-71625.408,-6821.467,10.000},
				{-72307.555,-6821.467,10.000},
				{-72989.702,-6821.467,10.000},
				{-73671.848,-6821.467,10.000},
				{-74353.995,-6821.467,10.000},
				{-75036.142,-6821.467,10.000},
				{-75718.289,-6821.467,10.000},
				{-76400.435,-6821.467,10.000},
				{-77082.582,-6821.467,10.000},
				{-77764.729,-6821.467,10.000},
				{-78446.876,-6821.467,10.000},
				{-79129.022,-6821.467,10.000},
				{-79811.169,-6821.467,10.000},
				{-80493.316,-6821.467,10.000},
				{-81175.463,-6821.467,10.000},
				{-81857.609,-6821.467,10.000},
				{-82539.756,-6821.467,10.000},
				{-83221.903,-6821.467,10.000},
				{-83904.050,-6821.467,10.000},
				{-84586.196,-6821.467,10.000},
				{-85268.343,-6821.467,10.000},
				{-85950.490,-6821.467,10.000},
				{-86632.637,-6821.467,10.000},
				{-87314.783,-6821.467,10.000},
				{-87996.930,-6821.467,10.000},
				{-88679.077,-6821.467,10.000},
				{-89361.223,-6821.467,10.000},
				{-90043.370,-6821.467,10.000},
				{-90725.517,-6821.467,10.000},
				{-91407.664,-6821.467,10.000},
				{-92089.810,-6821.467,10.000},
				{-92771.957,-6821.467,10.000},
				{-93454.104,-6821.467,10.000},
				{-94136.251,-6821.467,10.000},
				{-94818.397,-6821.467,10.000},
				{-95500.544,-6821.467,10.000},
				{-96182.691,-6821.467,10.000},
				{-96864.838,-6821.467,10.000},
				{-97546.984,-6821.467,10.000},
				{-98229.131,-6821.467,10.000},
				{-98911.278,-6821.467,10.000},
				{-99593.425,-6821.467,10.000},
				{-100275.571,-6821.467,10.000},
				{-100957.718,-6821.467,10.000},
				{-101639.865,-6821.467,10.000},
				{-102322.012,-6821.467,10.000},
				{-103004.158,-6821.467,10.000},
				{-103686.305,-6821.467,10.000},
				{-104368.452,-6821.467,10.000},
				{-105050.599,-6821.467,10.000},
				{-105732.745,-6821.467,10.000},
				{-106414.892,-6821.467,10.000},
				{-107097.039,-6821.467,10.000},
				{-107779.186,-6821.467,10.000},
				{-108461.332,-6821.467,10.000},
				{-109143.479,-6821.467,10.000},
				{-109825.626,-6821.467,10.000},
				{-110507.773,-6821.467,10.000},
				{-111189.919,-6821.467,10.000},
				{-111872.066,-6821.467,10.000},
				{-112554.213,-6821.467,10.000},
				{-113236.360,-6821.467,10.000},
				{-113918.506,-6821.467,10.000},
				{-114600.653,-6821.467,10.000},
				{-115282.800,-6821.467,10.000},
				{-115964.947,-6821.467,10.000},
				{-116647.093,-6821.467,10.000},
				{-117329.240,-6821.467,10.000},
				{-118011.387,-6821.467,10.000},
				{-118693.533,-6821.467,10.000},
				{-119375.680,-6821.467,10.000},
				{-120057.827,-6821.467,10.000},
				{-120739.974,-6821.467,10.000},
				{-121422.120,-6821.467,10.000},
				{-122104.267,-6821.467,10.000},
				{-122786.414,-6821.467,10.000},
				{-123468.561,-6821.467,10.000},
				{-124150.707,-6821.467,10.000},
				{-124832.854,-6821.467,10.000},
				{-125515.001,-6821.467,10.000},
				{-126197.148,-6821.467,10.000},
				{-126879.294,-6821.467,10.000},
				{-127561.441,-6821.467,10.000},
				{-128243.588,-6821.467,10.000},
				{-128925.735,-6821.467,10.000},
				{-129607.881,-6821.467,10.000},
				{-130290.028,-6821.467,10.000},
				{-130972.175,-6821.467,10.000},
				{-131654.322,-6821.467,10.000},
				{-132336.468,-6821.467,10.000},
				{-133018.615,-6821.467,10.000},
				{-133700.762,-6821.467,10.000},
				{-134382.909,-6821.467,10.000},
				{-135065.055,-6821.467,10.000},
				{-135747.202,-6821.467,10.000},
				{-136429.349,-6821.467,10.000},
				{-137111.496,-6821.467,10.000},
				{-137793.642,-6821.467,10.000},
				{-138475.789,-6821.467,10.000},
				{-139157.936,-6821.467,10.000},
				{-139840.083,-6821.467,10.000},
				{-140522.229,-6821.467,10.000},
				{-141204.376,-6821.467,10.000},
				{-141886.523,-6821.467,10.000},
				{-142568.670,-6821.467,10.000},
				{-143250.816,-6821.467,10.000},
				{-143932.963,-6821.467,10.000},
				{-144615.110,-6821.467,10.000},
				{-145297.256,-6821.467,10.000},
				{-145979.403,-6821.467,10.000},
				{-146661.550,-6821.467,10.000},
				{-147343.697,-6821.467,10.000},
				{-148025.843,-6821.467,10.000},
				{-148707.990,-6821.467,10.000},
				{-149390.137,-6821.467,10.000},
				{-150072.284,-6821.467,10.000},
				{-150754.430,-6821.467,10.000},
				{-151436.577,-6821.467,10.000},
				{-152118.222,-6816.452,10.000},
				{-152798.363,-6801.404,10.000},
				{-153475.995,-6776.325,10.000},
				{-154150.117,-6741.215,10.000},
				{-154819.724,-6696.073,10.000},
				{-155483.814,-6640.899,10.000},
				{-156141.383,-6575.694,10.000},
				{-156791.429,-6500.457,10.000},
				{-157432.948,-6415.189,10.000},
				{-158064.937,-6319.889,10.000},
				{-158686.393,-6214.557,10.000},
				{-159296.312,-6099.194,10.000},
				{-159893.692,-5973.800,10.000},
				{-160477.529,-5838.374,10.000},
				{-161046.821,-5692.916,10.000},
				{-161600.564,-5537.427,10.000},
				{-162137.754,-5371.906,10.000},
				{-162657.891,-5201.369,10.000},
				{-163160.974,-5030.832,10.000},
				{-163647.004,-4860.296,10.000},
				{-164115.980,-4689.759,10.000},
				{-164567.902,-4519.222,10.000},
				{-165002.771,-4348.685,10.000},
				{-165420.585,-4178.149,10.000},
				{-165821.347,-4007.612,10.000},
				{-166205.054,-3837.075,10.000},
				{-166571.708,-3666.539,10.000},
				{-166921.308,-3496.002,10.000},
				{-167253.855,-3325.465,10.000},
				{-167569.348,-3154.929,10.000},
				{-167867.787,-2984.392,10.000},
				{-168149.172,-2813.855,10.000},
				{-168413.504,-2643.319,10.000},
				{-168660.782,-2472.782,10.000},
				{-168891.007,-2302.245,10.000},
				{-169104.178,-2131.709,10.000},
				{-169300.295,-1961.172,10.000},
				{-169479.359,-1790.635,10.000},
				{-169641.368,-1620.099,10.000},
				{-169786.325,-1449.562,10.000},
				{-169914.729,-1284.041,10.000},
				{-170027.584,-1128.552,10.000},
				{-170125.893,-983.094,10.000},
				{-170210.660,-847.668,10.000},
				{-170282.887,-722.273,10.000},
				{-170343.578,-606.910,10.000},
				{-170393.736,-501.578,10.000},
				{-170434.364,-406.279,10.000},
				{-170466.465,-321.010,10.000},
				{-170491.042,-245.773,10.000},
				{-170509.099,-180.568,10.000},
				{-170521.639,-125.395,10.000},
				{-170529.664,-80.253,10.000},
				{-170534.178,-45.142,10.000},
				{-170536.184,-20.063,10.000},
				{-170536.686,-5.016,10.000},
				{-170536.686,-0.000,10.000}
		};
		
		double[][] rightPoints = {
				{-0.502,-10.032,10.000},
				{-2.508,-20.063,10.000},
				{-7.022,-45.142,10.000},
				{-15.047,-80.253,10.000},
				{-27.587,-125.395,10.000},
				{-45.644,-180.568,10.000},
				{-70.221,-245.773,10.000},
				{-102.322,-321.010,10.000},
				{-142.950,-406.279,10.000},
				{-193.108,-501.578,10.000},
				{-253.799,-606.910,10.000},
				{-326.026,-722.273,10.000},
				{-410.793,-847.668,10.000},
				{-509.102,-983.094,10.000},
				{-621.957,-1128.552,10.000},
				{-750.361,-1284.041,10.000},
				{-895.318,-1449.562,10.000},
				{-1057.327,-1620.099,10.000},
				{-1236.391,-1790.635,10.000},
				{-1432.508,-1961.172,10.000},
				{-1645.679,-2131.709,10.000},
				{-1875.904,-2302.245,10.000},
				{-2123.182,-2472.782,10.000},
				{-2387.514,-2643.319,10.000},
				{-2668.899,-2813.855,10.000},
				{-2967.338,-2984.392,10.000},
				{-3282.831,-3154.929,10.000},
				{-3615.378,-3325.465,10.000},
				{-3964.978,-3496.002,10.000},
				{-4331.632,-3666.539,10.000},
				{-4715.339,-3837.075,10.000},
				{-5116.101,-4007.612,10.000},
				{-5533.915,-4178.149,10.000},
				{-5968.784,-4348.685,10.000},
				{-6420.706,-4519.222,10.000},
				{-6889.682,-4689.759,10.000},
				{-7375.712,-4860.296,10.000},
				{-7878.795,-5030.832,10.000},
				{-8398.932,-5201.369,10.000},
				{-8936.122,-5371.906,10.000},
				{-9489.865,-5537.427,10.000},
				{-10059.157,-5692.916,10.000},
				{-10642.994,-5838.374,10.000},
				{-11240.374,-5973.800,10.000},
				{-11850.293,-6099.194,10.000},
				{-12471.749,-6214.557,10.000},
				{-13103.738,-6319.889,10.000},
				{-13745.257,-6415.189,10.000},
				{-14395.303,-6500.457,10.000},
				{-15052.872,-6575.694,10.000},
				{-15716.962,-6640.899,10.000},
				{-16386.569,-6696.073,10.000},
				{-17060.691,-6741.215,10.000},
				{-17738.323,-6776.325,10.000},
				{-18418.464,-6801.404,10.000},
				{-19100.109,-6816.452,10.000},
				{-19782.256,-6821.467,10.000},
				{-20464.402,-6821.467,10.000},
				{-21146.549,-6821.467,10.000},
				{-21828.696,-6821.467,10.000},
				{-22510.843,-6821.467,10.000},
				{-23192.989,-6821.467,10.000},
				{-23875.136,-6821.467,10.000},
				{-24557.283,-6821.467,10.000},
				{-25239.430,-6821.467,10.000},
				{-25921.576,-6821.467,10.000},
				{-26603.723,-6821.467,10.000},
				{-27285.870,-6821.467,10.000},
				{-27968.017,-6821.467,10.000},
				{-28650.163,-6821.467,10.000},
				{-29332.310,-6821.467,10.000},
				{-30014.457,-6821.467,10.000},
				{-30696.603,-6821.467,10.000},
				{-31378.750,-6821.467,10.000},
				{-32060.897,-6821.467,10.000},
				{-32743.044,-6821.467,10.000},
				{-33425.190,-6821.467,10.000},
				{-34107.337,-6821.467,10.000},
				{-34789.484,-6821.467,10.000},
				{-35471.631,-6821.467,10.000},
				{-36153.777,-6821.467,10.000},
				{-36835.924,-6821.467,10.000},
				{-37518.071,-6821.467,10.000},
				{-38200.218,-6821.467,10.000},
				{-38882.364,-6821.467,10.000},
				{-39564.511,-6821.467,10.000},
				{-40246.658,-6821.467,10.000},
				{-40928.805,-6821.467,10.000},
				{-41610.951,-6821.467,10.000},
				{-42293.098,-6821.467,10.000},
				{-42975.245,-6821.467,10.000},
				{-43657.392,-6821.467,10.000},
				{-44339.538,-6821.467,10.000},
				{-45021.685,-6821.467,10.000},
				{-45703.832,-6821.467,10.000},
				{-46385.979,-6821.467,10.000},
				{-47068.125,-6821.467,10.000},
				{-47750.272,-6821.467,10.000},
				{-48432.419,-6821.467,10.000},
				{-49114.566,-6821.467,10.000},
				{-49796.712,-6821.467,10.000},
				{-50478.859,-6821.467,10.000},
				{-51161.006,-6821.467,10.000},
				{-51843.153,-6821.467,10.000},
				{-52525.299,-6821.467,10.000},
				{-53207.446,-6821.467,10.000},
				{-53889.593,-6821.467,10.000},
				{-54571.740,-6821.467,10.000},
				{-55253.886,-6821.467,10.000},
				{-55936.033,-6821.467,10.000},
				{-56618.180,-6821.467,10.000},
				{-57300.327,-6821.467,10.000},
				{-57982.473,-6821.467,10.000},
				{-58664.620,-6821.467,10.000},
				{-59346.767,-6821.467,10.000},
				{-60028.913,-6821.467,10.000},
				{-60711.060,-6821.467,10.000},
				{-61393.207,-6821.467,10.000},
				{-62075.354,-6821.467,10.000},
				{-62757.500,-6821.467,10.000},
				{-63439.647,-6821.467,10.000},
				{-64121.794,-6821.467,10.000},
				{-64803.941,-6821.467,10.000},
				{-65486.087,-6821.467,10.000},
				{-66168.234,-6821.467,10.000},
				{-66850.381,-6821.467,10.000},
				{-67532.528,-6821.467,10.000},
				{-68214.674,-6821.467,10.000},
				{-68896.821,-6821.467,10.000},
				{-69578.968,-6821.467,10.000},
				{-70261.115,-6821.467,10.000},
				{-70943.261,-6821.467,10.000},
				{-71625.408,-6821.467,10.000},
				{-72307.555,-6821.467,10.000},
				{-72989.702,-6821.467,10.000},
				{-73671.848,-6821.467,10.000},
				{-74353.995,-6821.467,10.000},
				{-75036.142,-6821.467,10.000},
				{-75718.289,-6821.467,10.000},
				{-76400.435,-6821.467,10.000},
				{-77082.582,-6821.467,10.000},
				{-77764.729,-6821.467,10.000},
				{-78446.876,-6821.467,10.000},
				{-79129.022,-6821.467,10.000},
				{-79811.169,-6821.467,10.000},
				{-80493.316,-6821.467,10.000},
				{-81175.463,-6821.467,10.000},
				{-81857.609,-6821.467,10.000},
				{-82539.756,-6821.467,10.000},
				{-83221.903,-6821.467,10.000},
				{-83904.050,-6821.467,10.000},
				{-84586.196,-6821.467,10.000},
				{-85268.343,-6821.467,10.000},
				{-85950.490,-6821.467,10.000},
				{-86632.637,-6821.467,10.000},
				{-87314.783,-6821.467,10.000},
				{-87996.930,-6821.467,10.000},
				{-88679.077,-6821.467,10.000},
				{-89361.223,-6821.467,10.000},
				{-90043.370,-6821.467,10.000},
				{-90725.517,-6821.467,10.000},
				{-91407.664,-6821.467,10.000},
				{-92089.810,-6821.467,10.000},
				{-92771.957,-6821.467,10.000},
				{-93454.104,-6821.467,10.000},
				{-94136.251,-6821.467,10.000},
				{-94818.397,-6821.467,10.000},
				{-95500.544,-6821.467,10.000},
				{-96182.691,-6821.467,10.000},
				{-96864.838,-6821.467,10.000},
				{-97546.984,-6821.467,10.000},
				{-98229.131,-6821.467,10.000},
				{-98911.278,-6821.467,10.000},
				{-99593.425,-6821.467,10.000},
				{-100275.571,-6821.467,10.000},
				{-100957.718,-6821.467,10.000},
				{-101639.865,-6821.467,10.000},
				{-102322.012,-6821.467,10.000},
				{-103004.158,-6821.467,10.000},
				{-103686.305,-6821.467,10.000},
				{-104368.452,-6821.467,10.000},
				{-105050.599,-6821.467,10.000},
				{-105732.745,-6821.467,10.000},
				{-106414.892,-6821.467,10.000},
				{-107097.039,-6821.467,10.000},
				{-107779.186,-6821.467,10.000},
				{-108461.332,-6821.467,10.000},
				{-109143.479,-6821.467,10.000},
				{-109825.626,-6821.467,10.000},
				{-110507.773,-6821.467,10.000},
				{-111189.919,-6821.467,10.000},
				{-111872.066,-6821.467,10.000},
				{-112554.213,-6821.467,10.000},
				{-113236.360,-6821.467,10.000},
				{-113918.506,-6821.467,10.000},
				{-114600.653,-6821.467,10.000},
				{-115282.800,-6821.467,10.000},
				{-115964.947,-6821.467,10.000},
				{-116647.093,-6821.467,10.000},
				{-117329.240,-6821.467,10.000},
				{-118011.387,-6821.467,10.000},
				{-118693.533,-6821.467,10.000},
				{-119375.680,-6821.467,10.000},
				{-120057.827,-6821.467,10.000},
				{-120739.974,-6821.467,10.000},
				{-121422.120,-6821.467,10.000},
				{-122104.267,-6821.467,10.000},
				{-122786.414,-6821.467,10.000},
				{-123468.561,-6821.467,10.000},
				{-124150.707,-6821.467,10.000},
				{-124832.854,-6821.467,10.000},
				{-125515.001,-6821.467,10.000},
				{-126197.148,-6821.467,10.000},
				{-126879.294,-6821.467,10.000},
				{-127561.441,-6821.467,10.000},
				{-128243.588,-6821.467,10.000},
				{-128925.735,-6821.467,10.000},
				{-129607.881,-6821.467,10.000},
				{-130290.028,-6821.467,10.000},
				{-130972.175,-6821.467,10.000},
				{-131654.322,-6821.467,10.000},
				{-132336.468,-6821.467,10.000},
				{-133018.615,-6821.467,10.000},
				{-133700.762,-6821.467,10.000},
				{-134382.909,-6821.467,10.000},
				{-135065.055,-6821.467,10.000},
				{-135747.202,-6821.467,10.000},
				{-136429.349,-6821.467,10.000},
				{-137111.496,-6821.467,10.000},
				{-137793.642,-6821.467,10.000},
				{-138475.789,-6821.467,10.000},
				{-139157.936,-6821.467,10.000},
				{-139840.083,-6821.467,10.000},
				{-140522.229,-6821.467,10.000},
				{-141204.376,-6821.467,10.000},
				{-141886.523,-6821.467,10.000},
				{-142568.670,-6821.467,10.000},
				{-143250.816,-6821.467,10.000},
				{-143932.963,-6821.467,10.000},
				{-144615.110,-6821.467,10.000},
				{-145297.256,-6821.467,10.000},
				{-145979.403,-6821.467,10.000},
				{-146661.550,-6821.467,10.000},
				{-147343.697,-6821.467,10.000},
				{-148025.843,-6821.467,10.000},
				{-148707.990,-6821.467,10.000},
				{-149390.137,-6821.467,10.000},
				{-150072.284,-6821.467,10.000},
				{-150754.430,-6821.467,10.000},
				{-151436.577,-6821.467,10.000},
				{-152118.222,-6816.452,10.000},
				{-152798.363,-6801.404,10.000},
				{-153475.995,-6776.325,10.000},
				{-154150.117,-6741.215,10.000},
				{-154819.724,-6696.073,10.000},
				{-155483.814,-6640.899,10.000},
				{-156141.383,-6575.694,10.000},
				{-156791.429,-6500.457,10.000},
				{-157432.948,-6415.189,10.000},
				{-158064.937,-6319.889,10.000},
				{-158686.393,-6214.557,10.000},
				{-159296.312,-6099.194,10.000},
				{-159893.692,-5973.800,10.000},
				{-160477.529,-5838.374,10.000},
				{-161046.821,-5692.916,10.000},
				{-161600.564,-5537.427,10.000},
				{-162137.754,-5371.906,10.000},
				{-162657.891,-5201.369,10.000},
				{-163160.974,-5030.832,10.000},
				{-163647.004,-4860.296,10.000},
				{-164115.980,-4689.759,10.000},
				{-164567.902,-4519.222,10.000},
				{-165002.771,-4348.685,10.000},
				{-165420.585,-4178.149,10.000},
				{-165821.347,-4007.612,10.000},
				{-166205.054,-3837.075,10.000},
				{-166571.708,-3666.539,10.000},
				{-166921.308,-3496.002,10.000},
				{-167253.855,-3325.465,10.000},
				{-167569.348,-3154.929,10.000},
				{-167867.787,-2984.392,10.000},
				{-168149.172,-2813.855,10.000},
				{-168413.504,-2643.319,10.000},
				{-168660.782,-2472.782,10.000},
				{-168891.007,-2302.245,10.000},
				{-169104.178,-2131.709,10.000},
				{-169300.295,-1961.172,10.000},
				{-169479.359,-1790.635,10.000},
				{-169641.368,-1620.099,10.000},
				{-169786.325,-1449.562,10.000},
				{-169914.729,-1284.041,10.000},
				{-170027.584,-1128.552,10.000},
				{-170125.893,-983.094,10.000},
				{-170210.660,-847.668,10.000},
				{-170282.887,-722.273,10.000},
				{-170343.578,-606.910,10.000},
				{-170393.736,-501.578,10.000},
				{-170434.364,-406.279,10.000},
				{-170466.465,-321.010,10.000},
				{-170491.042,-245.773,10.000},
				{-170509.099,-180.568,10.000},
				{-170521.639,-125.395,10.000},
				{-170529.664,-80.253,10.000},
				{-170534.178,-45.142,10.000},
				{-170536.184,-20.063,10.000},
				{-170536.686,-5.016,10.000},
				{-170536.686,-0.000,10.000}
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