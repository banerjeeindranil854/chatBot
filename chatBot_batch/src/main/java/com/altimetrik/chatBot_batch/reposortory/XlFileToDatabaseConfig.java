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

	/*
	 * @Bean public ItemReader<OutReachRegisteredDTO> xlOutReachRegisterReader()
	 * throws MalformedURLException { PoiItemReader<OutReachRegisteredDTO> reader =
	 * new PoiItemReader<>();
	 * 
	 * reader.setLinesToSkip(1); reader.setResource(new UrlResource(FILE +
	 * register)); reader.setRowMapper(new RegisterUserRowmapper()); return reader;
	 * }
	 * 
	 * @Bean public ItemReader<OutReachEventSummeryDTO>
	 * xlOutReachEventSummeryReader() throws MalformedURLException {
	 * PoiItemReader<OutReachEventSummeryDTO> reader = new PoiItemReader<>();
	 * 
	 * reader.setLinesToSkip(1); reader.setResource(new UrlResource(FILE +
	 * eventsummery)); reader.setRowMapper(new MenuRelationRowmapper()); return
	 * reader; }
	 * 
	 * @Bean public ItemReader<OutReachEventInfoDTO> xlOutReachEventInfoReader()
	 * throws MalformedURLException { PoiItemReader<OutReachEventInfoDTO> reader =
	 * new PoiItemReader<>();
	 * 
	 * reader.setLinesToSkip(1); reader.setResource(new UrlResource(FILE +
	 * eventinfo)); reader.setRowMapper(new EventInfoRowmapper()); return reader; }
	 */
	
	/*
	 * @Bean public ItemReader<OutReachEventPmoDTO> xlOutReachEventPmoReader()
	 * throws MalformedURLException { PoiItemReader<OutReachEventPmoDTO> reader =
	 * new PoiItemReader<>();
	 * 
	 * reader.setLinesToSkip(1); reader.setResource(new UrlResource(FILE +
	 * eventpmo)); reader.setRowMapper(new EventPmoRowMapper()); return reader; }
	 */
	@Bean
	ItemProcessor<Menu, Menu> menuProcessor() {
		return new MenuProcessor();
	}
	@Bean
	ItemProcessor<MenuRelation, MenuRelation> menuRelationProcessor() {
		return new MenuRelationPrecessor();
	}

	/*
	 * @Bean ItemProcessor<OutReachRegisteredDTO, OutReachRegisteredDTO>
	 * xlOutReachRegisteredProcessor() { return new OutReachRegisteredProcessor(); }
	 * 
	 * @Bean ItemProcessor<OutReachEventSummeryDTO, OutReachEventSummeryDTO>
	 * xlOutReachEventSummeryProcessor() { return new
	 * OutReachEventSummeryProcessor(); }
	 * 
	 * @Bean ItemProcessor<OutReachEventInfoDTO, OutReachEventInfoDTO>
	 * xlOutReachEventInfoProcessor() { return new MenuProcessor(); }
	 * 
	 * @Bean ItemProcessor<OutReachEventPmoDTO, OutReachEventPmoDTO>
	 * xlOutReachEventPmoProcessor() { return new MenuRelationPrecessor(); }
	 */

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

	/*
	 * @Bean public JdbcBatchItemWriter<OutReachRegisteredDTO>
	 * xlOutReachRegisteredWriter() { JdbcBatchItemWriter<OutReachRegisteredDTO>
	 * xlOutReachRegisteredWriter = new
	 * JdbcBatchItemWriter<OutReachRegisteredDTO>();
	 * xlOutReachRegisteredWriter.setItemSqlParameterSourceProvider( new
	 * BeanPropertyItemSqlParameterSourceProvider<OutReachRegisteredDTO>());
	 * xlOutReachRegisteredWriter.setSql(
	 * "INSERT INTO hackfac.outreach_registered (event_id, event_name, beneficiary_name, base_location, event_date, emp_id) VALUES (:eventId, :eventName, :beneficiaryName, :baseLocation, :eventDate, :empId)"
	 * ); xlOutReachRegisteredWriter.setDataSource(dataSource); return
	 * xlOutReachRegisteredWriter; }
	 */

	/*
	 * @Bean public JdbcBatchItemWriter<OutReachEventSummeryDTO>
	 * xlOutReachEventSummeryWriter() { JdbcBatchItemWriter<OutReachEventSummeryDTO>
	 * xlOutReachEventSummeryWriter = new
	 * JdbcBatchItemWriter<OutReachEventSummeryDTO>();
	 * xlOutReachEventSummeryWriter.setItemSqlParameterSourceProvider( new
	 * BeanPropertyItemSqlParameterSourceProvider<OutReachEventSummeryDTO>());
	 * xlOutReachEventSummeryWriter.setSql(
	 * "INSERT INTO hackfac.outreach_event_summery (event_id, month, base_location, beneficiary_name, venue_address, council_name, project, category, event_name, event_description, event_date, total_volunteer, total_volunteer_hour, total_travle_houres, overall_volunteer_hours) VALUES (:eventId, :month, :baseLocation, :beneficiaryName, :venueAddress, :councilName, :project, :category, :eventName, :eventDescription, :eventDate, :totalVolunteer, :totalVolunteerHour, :totalTravleHoures, :overallVolunteerHours)"
	 * ); xlOutReachEventSummeryWriter.setDataSource(dataSource); return
	 * xlOutReachEventSummeryWriter; }
	 */
	
	/*
	 * @Bean public JdbcBatchItemWriter<OutReachEventInfoDTO>
	 * xlOutReachEventInfoWriter() { JdbcBatchItemWriter<OutReachEventInfoDTO>
	 * xlOutReachEventInfoWriter = new JdbcBatchItemWriter<OutReachEventInfoDTO>();
	 * xlOutReachEventInfoWriter.setItemSqlParameterSourceProvider( new
	 * BeanPropertyItemSqlParameterSourceProvider<OutReachEventInfoDTO>());
	 * xlOutReachEventInfoWriter.setSql(
	 * "INSERT INTO hackfac.outreach_event_info (event_id, base_location, beneficiary_name, council_name, event_name, event_description, event_date, emp_id, emp_name, volunteer_hour, travle_houres, lives_impacted, business_unit, status, iiep_category) VALUES (:eventId, :baseLocation, :beneficiaryName, :councilName, :eventName, :eventDescription, :eventDate, :empId, :empName, :volunteerHour, :travleHoures, :livesImpacted, :businessUnit, :status, :iIEPCategory)"
	 * ); xlOutReachEventInfoWriter.setDataSource(dataSource); return
	 * xlOutReachEventInfoWriter; }
	 */
	
	/*
	 * @Bean public JdbcBatchItemWriter<OutReachEventPmoDTO>
	 * xlOutReachEventPmoWriter() { JdbcBatchItemWriter<OutReachEventPmoDTO>
	 * xlOutReachEventInfoWriter = new JdbcBatchItemWriter<OutReachEventPmoDTO>();
	 * xlOutReachEventInfoWriter.setItemSqlParameterSourceProvider( new
	 * BeanPropertyItemSqlParameterSourceProvider<OutReachEventPmoDTO>());
	 * xlOutReachEventInfoWriter.setSql(
	 * "INSERT INTO hackfac.outreach_event_pmo (event_id, emp_id) VALUES (:eventId, :empId)"
	 * ); xlOutReachEventInfoWriter.setDataSource(dataSource); return
	 * xlOutReachEventInfoWriter; }
	 */

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
	/*
	 * @Bean public Step xlFileToDatabaseStep2() throws MalformedURLException {
	 * return
	 * stepBuilderFactory.get("XlFileToDatabaseStep2").<OutReachRegisteredDTO,
	 * OutReachRegisteredDTO>chunk(10)
	 * .reader(xlOutReachRegisterReader()).processor(xlOutReachRegisteredProcessor()
	 * ) .writer(xlOutReachRegisteredWriter()).build(); }
	 */

	/*
	 * @Bean public Step xlFileToDatabaseStep3() throws MalformedURLException {
	 * return stepBuilderFactory.get("XlFileToDatabaseStep3")
	 * .<OutReachEventSummeryDTO,
	 * OutReachEventSummeryDTO>chunk(10).reader(xlOutReachEventSummeryReader())
	 * .processor(xlOutReachEventSummeryProcessor()).writer(
	 * xlOutReachEventSummeryWriter()).build(); }
	 */
	
	/*
	 * @Bean public Step xlFileToDatabaseStep4() throws MalformedURLException {
	 * return stepBuilderFactory.get("XlFileToDatabaseStep4")
	 * .<OutReachEventInfoDTO,
	 * OutReachEventInfoDTO>chunk(10).reader(xlOutReachEventInfoReader())
	 * .processor(xlOutReachEventInfoProcessor()).writer(xlOutReachEventInfoWriter()
	 * ).build(); }
	 */
	
	/*
	 * @Bean public Step xlFileToDatabaseStep5() throws MalformedURLException {
	 * return stepBuilderFactory.get("XlFileToDatabaseStep5") .<OutReachEventPmoDTO,
	 * OutReachEventPmoDTO>chunk(10).reader(xlOutReachEventPmoReader())
	 * .processor(xlOutReachEventPmoProcessor()).writer(xlOutReachEventPmoWriter()).
	 * build(); }
	 */

	@Bean
	Job csvFileToDatabaseJob(JobCompletionNotificationListener listener,JobBuilderFactory jobBuilderFactory) throws MalformedURLException {
		return jobBuilderFactory.get("XlFileToDatabaseJob").incrementer(new RunIdIncrementer()).listener(listener)
				.start(xlFileToDatabaseStep1()).next(xlFileToDatabaseStep2()).build();
	}
	// end job info
}
