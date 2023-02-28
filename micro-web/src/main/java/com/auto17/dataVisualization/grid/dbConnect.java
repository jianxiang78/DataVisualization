package com.auto17.dataVisualization.grid;

import com.auto17.dataVisualization.DTO.Iot;
import com.toshiba.mwcloud.gs.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class dbConnect {

	private final static Logger logger = LoggerFactory.getLogger(dbConnect.class);
	private static GridStore gridStore;

	public static void getConnect() throws GSException {
		if(gridStore==null){
			Properties props = new Properties();
			props.setProperty("notificationAddress", dbConfig.notificationAddress);
			props.setProperty("notificationPort", dbConfig.notificationPort);
			props.setProperty("clusterName", dbConfig.clusterName);
			props.setProperty("user", dbConfig.user);
			props.setProperty("password", dbConfig.password);
			gridStore = GridStoreFactory.getInstance().getGridStore(props);
		}
	}

	public static void close() throws GSException {
		if(gridStore!=null){
			gridStore.close();
		}
	}

	public static void put(String containerName,Iot iot) {
		try {
			getConnect();
			logger.info("begin containerName="+containerName+"**put**="+iot.getName()+"="+iot.getValue());
			TimeSeries<Iot> timeSeries = gridStore.putTimeSeries(containerName,Iot.class);
			timeSeries.append(iot);
			logger.info("end containerName="+containerName+"**put**="+iot.getName()+"="+iot.getValue());
		} catch (GSException e) {
			e.printStackTrace();
		}
	}

	public static Iot queryTopOne(String containerName){
		try {
			getConnect();
			TimeSeries<Iot> timeSeries = gridStore.putTimeSeries(containerName,Iot.class);
			Query<Iot> iotQuery=timeSeries
					.query("select * order by timestamp desc limit 1");
			RowSet<Iot> rowSet=iotQuery.fetch(false);
			logger.info("containerName="+containerName+"**size**="+rowSet.size());
			if(rowSet.hasNext()){
				return rowSet.next();
			}else {
				return null;
			}
		} catch (GSException e) {
			e.printStackTrace();
			return null;
		}
	}


}
