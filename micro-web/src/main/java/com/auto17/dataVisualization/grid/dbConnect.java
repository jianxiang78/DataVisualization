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
			props.setProperty("host", dbConfig.host);
			props.setProperty("port", dbConfig.port);
			props.setProperty("clusterName", dbConfig.clusterName);
			props.setProperty("database", "public");
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
		TimeSeries<Iot> timeSeries=null;
		try {
			getConnect();
			logger.info("begin containerName="+containerName+"**put**="+iot.getName()+"="+iot.getValue());
			timeSeries = gridStore.putTimeSeries(containerName,Iot.class);
			timeSeries.append(iot);
			logger.info("end containerName="+containerName+"**put**="+iot.getName()+"="+iot.getValue());
			timeSeries.close();
		} catch (GSException e) {
			e.printStackTrace();
		}finally {
			if(timeSeries!=null){
				try {
					timeSeries.close();
				} catch (GSException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static Iot queryTopOne(String containerName){
		Query<Iot> iotQuery= null;
		TimeSeries<Iot> timeSeries = null;
		try {
			getConnect();
			timeSeries = gridStore.putTimeSeries(containerName,Iot.class);
			iotQuery=timeSeries
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
		}finally {
			try {
				if(iotQuery!=null){
					iotQuery.close();
				}
				if(timeSeries!=null){
					timeSeries.close();
				}
			} catch (GSException e) {
				e.printStackTrace();
			}
		}
	}


}
