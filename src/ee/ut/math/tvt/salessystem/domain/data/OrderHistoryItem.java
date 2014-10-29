package ee.ut.math.tvt.salessystem.domain.data;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class OrderHistoryItem implements DisplayableItem {
	private String date;
	private String time;
	private double price;
	private List<SoldItem> items;
	
	
	public OrderHistoryItem(double price, List<SoldItem> list) {
		this.items = list;
		this.price = price;
		DateFormat DateFormat = new SimpleDateFormat("yyyy:MM:dd");
		DateFormat TimeFormat = new SimpleDateFormat("hh:mm:ss");
		
		Date date = new Date();
		this.date = DateFormat.format(date);
		this.time = TimeFormat.format(date);
	}

	public List<SoldItem> getItems() {
		return items;
	}

	public void setItems(List<SoldItem> items) {
		this.items = items;
	}

	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public void setId(long id) {
		Id = id;
	}
	private long Id;
	
	@Override
	public Long getId() {
		return Id;
	}
	
	@Override
	public boolean equals(Object obj) {
		return ((OrderHistoryItem)obj).getId() == this.getId();
	}

}
