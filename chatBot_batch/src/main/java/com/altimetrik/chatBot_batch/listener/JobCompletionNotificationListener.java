package com.altimetrik.chatBot_batch.listener;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.altimetrik.chatBot_batch.model.Menu;
import com.altimetrik.chatBot_batch.model.MenuRelation;

@Component
public class JobCompletionNotificationListener extends JobExecutionListenerSupport {

	private static final Logger log = LoggerFactory.getLogger(JobCompletionNotificationListener.class);

	private final JdbcTemplate jdbcTemplate;
	
	private String CONST_STRING="Discovered <{}> in the database.";

	@Autowired
	public JobCompletionNotificationListener(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public void afterJob(JobExecution jobExecution) {
		if(jobExecution.getStatus() == BatchStatus.COMPLETED) {
			log.info("============ JOB FINISHED ============ Verifying the results....\n");

			List<Menu> results1 = jdbcTemplate.query("select a.menu_id,a.menu_name,a.counter,a.type,a.description from chatbot.menu a", new RowMapper<Menu>() {
				@Override
				public Menu mapRow(ResultSet rs, int row) throws SQLException {
					return new Menu(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getString(5));
				}
			});
			
			List<MenuRelation> results2 = jdbcTemplate.query("select z.parent_id,z.child_id,z.id from chatbot.menu_relation z", new RowMapper<MenuRelation>() {
				@Override
				public MenuRelation mapRow(ResultSet rs, int row) throws SQLException {
					return new MenuRelation(rs.getInt(3), rs.getBigDecimal(1), rs.getBigDecimal(2));
				}
			});
			
			
			for (Menu menu : results1) {
				log.info(CONST_STRING,menu);
			}
			
			for (MenuRelation menuRelation : results2) {
				log.info(CONST_STRING,menuRelation);
			}
			
		}
	}
	
}
