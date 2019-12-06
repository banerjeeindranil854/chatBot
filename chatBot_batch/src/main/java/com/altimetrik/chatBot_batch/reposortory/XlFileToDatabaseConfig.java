package com.altimetrik.chatBot_batch.reposortory;

import java.net.MalformedURLException;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.excel.poi.PoiItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.UrlResource;

import com.altimetrik.chatBot_batch.listener.JobCompletionNotificationListener;
import com.altimetrik.chatBot_batch.model.Menu;
import com.altimetrik.chatBot_batch.model.MenuRelation;
import com.altimetrik.chatBot_batch.processoe.MenuProcessor;
import com.altimetrik.chatBot_batch.processoe.MenuRelationPrecessor;
import com.altimetrik.chatBot_batch.rowmapper.MenuRelationRowmapper;
import com.altimetrik.chatBot_batch.rowmapper.MenuRowmapper;

@EnableBatchProcessing
@Configuration
public class XlFileToDatabaseConfig {

	@Value("${chatbot.menu}")
	private String menu;

	
	  @Value("${chatbot.menuReletion}") 
	  private String menuReletion;
	 

	private String FILE="file:";

	@Autowired
	public StepBuilderFactory stepBuilderFactory;

	@Autowired
	public DataSource dataSource;

	// begin reader, writer, and processor

	@Bean
	public ItemReader<Menu> menuReader() throws MalformedURLException {
		PoiItemReader<Menu> reader = new PoiItemReader<>();

		reader.setLinesToSkip(1);
		reader.setResource(new UrlResource(FILE + menu));
		reader.setRowMapper(new MenuRowmapper());
		return reader;
	}
	
	@Bean
	public ItemReader<MenuRelation> menuRelationReader() throws MalformedURLException {
		PoiItemReader<MenuRelation> reader = new PoiItemReader<>();

		reader.setLinesToSkip(1);
		reader.setResource(new UrlResource(FILE + menuReletion));
		reader.setRowMapper(new MenuRelationRowmapper());
		return reader;
	}

	
	
	
	@Bean
	ItemProcessor<Menu, Menu> menuProcessor() {
		return new MenuProcessor();
	}
	@Bean
	ItemProcessor<MenuRelation, MenuRelation> menuRelationProcessor() {
		return new MenuRelationPrecessor();
	}

	

	@Bean
	public JdbcBatchItemWriter<Menu> menuWriter() {
		JdbcBatchItemWriter<Menu> MenuWriter = new JdbcBatchItemWriter<Menu>();
		MenuWriter.setItemSqlParameterSourceProvider(
				new BeanPropertyItemSqlParameterSourceProvider<Menu>());
		MenuWriter.setSql(
				"INSERT INTO chatbot.menu (menu_id, menu_name, counter, type, description) VALUES (:id, :menuName, :count, :type, :description)");
		MenuWriter.setDataSource(dataSource);
		return MenuWriter;
	}
	@Bean
	public JdbcBatchItemWriter<MenuRelation> menuRelationWriter() {
		JdbcBatchItemWriter<MenuRelation> MenuWriter = new JdbcBatchItemWriter<MenuRelation>();
		MenuWriter.setItemSqlParameterSourceProvider(
				new BeanPropertyItemSqlParameterSourceProvider<MenuRelation>());
		MenuWriter.setSql(
				"INSERT INTO chatbot.menu_relation (id, parent_id, child_id) VALUES (:id, :parentNode, :childNode)");
		MenuWriter.setDataSource(dataSource);
		return MenuWriter;
	}

		// end reader, writer, and processor

	// begin job info
	@Bean
	public Step xlFileToDatabaseStep1() throws MalformedURLException {
		return stepBuilderFactory.get("XlFileToDatabaseStep1")
				.<Menu, Menu>chunk(10).reader(menuReader())
				.processor(menuProcessor()).writer(menuWriter()).build();
	}
	@Bean
	public Step xlFileToDatabaseStep2() throws MalformedURLException {
		return stepBuilderFactory.get("xlFileToDatabaseStep2")
				.<MenuRelation, MenuRelation>chunk(10).reader(menuRelationReader())
				.processor(menuRelationProcessor()).writer(menuRelationWriter()).build();
	}
	
	@Bean
	Job csvFileToDatabaseJob(JobCompletionNotificationListener listener,JobBuilderFactory jobBuilderFactory) throws MalformedURLException {
		return jobBuilderFactory.get("XlFileToDatabaseJob").incrementer(new RunIdIncrementer()).listener(listener)
				.start(xlFileToDatabaseStep1()).next(xlFileToDatabaseStep2()).build();
	}
	// end job info
}
