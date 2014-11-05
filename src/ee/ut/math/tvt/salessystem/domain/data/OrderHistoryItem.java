package ee.ut.math.tvt.salessystem.domain.data;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="SALE")
public class OrderHistoryItem implements DisplayableItem {
	
	@Id
	private Long id;
	@Column(name="DATE")
	private Timestamp time;
	@Column(name="PRICE")
	private double price;
	
	@OneToMany(mappedBy ="history")
	private List<SoldItem> items;
	
	public OrderHistoryItem(double price, List<SoldItem> list) {
		this.items = list;
		this.price = price;
		Calendar calendar = Calendar.getInstance();
		Date now = calendar.getTime();
		this.time = new Timestamp(now.getTime());
	}

	public List<SoldItem> getItems() {
		return items;
	}

	public void setItems(List<SoldItem> items) {
		this.items = items;
	}

	public Timestamp getTime() {
		return time;
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
