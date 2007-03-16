/**
 * AICScore.java
 *
 * This file is part of the Java Machine Learning API
 * 
 * The Java Machine Learning API is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * The Java Machine Learning API is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with the Java Machine Learning API; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 * 
 * Copyright (c) 2006-2007, Andreas De Rijcke
 * 
 * Project: http://sourceforge.net/projects/java-ml/
 * 
 */


package net.sf.javaml.clustering.evaluation;

import net.sf.javaml.core.Dataset;
import net.sf.javaml.utils.*;

/**
 * TODO check code
 * 
 * @author Andreas De Rijcke
 *
 */
public class AICScore implements ClusterEvaluation {
	
	public double score(Dataset[] clusters) {
		
		LogLikelihoodFunction likelihood = new LogLikelihoodFunction();
		double l = likelihood.loglikelihoodsum(clusters);
		double k = 1;
		double aic = -2*l+2*k;
		return aic;
	}
	
	
	/*public double score(Clusterer c, Dataset data) {
		Dataset[] datas = new Dataset[c.getNumberOfClusters()];
		for (int i = 0; i < c.getNumberOfClusters(); i++) {
			datas[i] = new SimpleDataset();
		}
		for (int i = 0; i < data.size(); i++) {
			Instance in = data.getInstance(i);
			datas[c.predictCluster(in)].addInstance(in);
		}

		// calculate centroids
		int instanceLength = data.getInstance(0).size();
		double[][] sumPosition = new double[c.getNumberOfClusters()][instanceLength];
		int[] countPosition = new int[c.getNumberOfClusters()];
		for (int i = 0; i < data.size(); i++) {
			Instance in = data.getInstance(i);
			int predictedIndex = c.predictCluster(in);
			for (int j = 0; j < instanceLength; j++) {

				sumPosition[predictedIndex][j] += in.getWeight()
						* in.getValue(j);

			}
			countPosition[predictedIndex]++;
		}
		// DistanceMeasure
		Instance[] centroids = new Instance[c.getNumberOfClusters()];
		for (int i = 0; i < c.getNumberOfClusters(); i++) {
			float[] tmp = new float[instanceLength];
			for (int j = 0; j < instanceLength; j++) {
				tmp[j] = (float) sumPosition[i][j] / countPosition[i];
			}
			centroids[i] = new SimpleInstance(tmp);
		}

		// calculate bic
		double k = c.getNumberOfClusters();
		double overAllVariance = 0, overAllLoglike = 0, aic = 0;
		for (int i = 0; i < k; i++) {
			// calculate cluster variances
			double s = datas[i].size(), sum = 0;
			DistanceMeasure dm = DistanceMeasureFactory
					.getEuclideanDistanceMeasure();
			for (int j = 0; j < s; j++) {
				sum = 0;
				sum += dm.calculateDistance(datas[i].getInstance(j),
						centroids[i]);
			}
			double variance = sum / s;
			overAllVariance += variance;
			// calculate cluster loglikes
			double loglike = (-s / 2) * (Math.log(2 * Math.PI))
					- ((s * instanceLength) / 2) * (Math.log(variance)) + s
					* (Math.log(s)) - s * (Math.log(data.size())) - (s - k) / 2;
			overAllLoglike += Math.abs(loglike);
		}
		overAllVariance /= k;
		double p = (k - 1) + instanceLength * k + overAllVariance;
		aic = -2*overAllLoglike+2*p;;
		return aic;
	}*/

	public boolean compareScore(double score1, double score2) {
		// should be minimalized
		return Math.abs(score2) < Math.abs(score1);
	}

	
}
