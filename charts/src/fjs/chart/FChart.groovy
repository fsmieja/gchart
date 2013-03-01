package fjs.chart

import groovy.swing.SwingBuilder

import java.awt.*

import javax.swing.WindowConstants as WC

import org.jfree.chart.ChartFactory
import org.jfree.chart.ChartPanel
import org.jfree.chart.ChartUtilities
import org.jfree.chart.JFreeChart
import org.jfree.chart.plot.PlotOrientation
import org.jfree.data.category.DefaultCategoryDataset
import org.jfree.data.general.DefaultPieDataset
import org.jfree.data.time.Day
import org.jfree.data.time.Hour
import org.jfree.data.time.Minute
import org.jfree.data.time.Month
import org.jfree.data.time.TimeSeries
import org.jfree.data.time.TimeSeriesCollection
import org.jfree.data.xy.XYDataset

class FChart {

	JFreeChart chart
	 
	void drawPie() {
		def piedataset = new DefaultPieDataset();
		piedataset.with {
			setValue "Apr", 10
			setValue "May", 30
			setValue "June", 40
		}

		def options = [true, true, true]
		chart = ChartFactory.createPieChart("Pie Chart Sample",
				piedataset, *options)
		chart.backgroundPaint = Color.white
		def swing = new SwingBuilder()
		def frame = swing.frame(title:'Groovy PieChart',
		defaultCloseOperation:WC.EXIT_ON_CLOSE) {
			panel(id:'canvas') { widget(new ChartPanel(chart)) }
		}
		frame.pack()
		frame.show()
	}

	void drawLinePlot() {

		def dataset = new DefaultCategoryDataset()
		dataset.with{
			addValue 150, "no.1", "Jan"
			addValue 210, "no.1", "Feb"
			addValue 390, "no.1", "Mar"
			addValue 300, "no.2", "Jan"
			addValue 400, "no.2", "Feb"
			addValue 200, "no.2", "Mar"
		}

		def labels = ["Bugs", "Month", "Count"]
		def options = [true, true, true]
		chart = ChartFactory.createLineChart(*labels, dataset,
				PlotOrientation.VERTICAL, *options)
		def swing = new SwingBuilder()
		def frame = swing.frame(title:'Groovy LineChart',
		defaultCloseOperation:WC.EXIT_ON_CLOSE) {
			panel(id:'canvas') { widget(new ChartPanel(chart)) }
		}
		frame.pack()
		frame.show()
	}



	/**
	 * Creates a dataset, consisting of two series of monthly data.
	 *
	 * @return The dataset.
	 */
	private XYDataset createXYDataset() {

		TimeSeries s1 = new TimeSeries("L&G European Index Trust");
		s1.add(new Month(2, 2001), 181.8);
		s1.add(new Month(3, 2001), 167.3);
		s1.add(new Month(4, 2001), 153.8);
		s1.add(new Month(5, 2001), 167.6);
		s1.add(new Month(6, 2001), 158.8);
		s1.add(new Month(7, 2001), 148.3);
		s1.add(new Month(8, 2001), 153.9);
		s1.add(new Month(9, 2001), 142.7);
		s1.add(new Month(10, 2001), 123.2);
		s1.add(new Month(11, 2001), 131.8);
		s1.add(new Month(12, 2001), 139.6);
		s1.add(new Month(1, 2002), 142.9);
		s1.add(new Month(2, 2003), 138.7);
		s1.add(new Month(3, 2002), 137.3);
		s1.add(new Month(4, 2002), 143.9);
		s1.add(new Month(5, 2002), 139.8);
		s1.add(new Month(6, 2002), 137.0);
		s1.add(new Month(7, 2002), 132.8);

		TimeSeries s2 = new TimeSeries("L&G UK Index Trust");
		s2.add(new Month(2, 2001), 129.6);
		s2.add(new Month(3, 2001), 123.2);
		s2.add(new Month(4, 2001), 117.2);
		s2.add(new Month(5, 2001), 124.1);
		s2.add(new Month(6, 2001), 122.6);
		s2.add(new Month(7, 2001), 119.2);
		s2.add(new Month(8, 2001), 116.5);
		s2.add(new Month(9, 2001), 112.7);
		s2.add(new Month(10, 2001), 101.5);
		s2.add(new Month(11, 2001), 106.1);
		s2.add(new Month(12, 2001), 110.3);
		s2.add(new Month(1, 2002), 111.7);
		s2.add(new Month(2, 2002), 111.0);
		s2.add(new Month(3, 2002), 109.6);
		s2.add(new Month(4, 2002), 113.2);
		s2.add(new Month(5, 2002), 111.6);
		s2.add(new Month(6, 2002), 108.8);
		s2.add(new Month(7, 2002), 101.6);

		TimeSeries s3 = new TimeSeries("Days not months")
		s3.add(new Day(21, 2, 2000), 438.7);
		s3.add(new Day(1, 12, 2000), 138.7);
		
		TimeSeries s4 = new TimeSeries("Minutes")
		
		s4.add(new Minute(50, new Hour(1, new Day(21, 2, 2000))), 138.7);
		s4.add(new Minute(10, new Hour(12, new Day(21, 2, 2000))), 438.7);

		
		
		TimeSeriesCollection dataset = new TimeSeriesCollection();
		//dataset.addSeries(s1);
		//dataset.addSeries(s2);
		//dataset.addSeries(s3);
		dataset.addSeries(s4);
		
		return dataset;

	}
	void drawTimeSeries() {

		def dataset = createXYDataset();

		def labels = ["Bugs", "Month", "Count"]
		def options = [true, true, true]
		chart = ChartFactory.createTimeSeriesChart(*labels, dataset, *options)
//		chart.xYPlot.set
		def swing = new SwingBuilder()
		def frame = swing.frame(title:'Groovy Time Series',
		defaultCloseOperation:WC.EXIT_ON_CLOSE) {
			panel(id:'canvas') { widget(new ChartPanel(chart)) }
		}
		frame.pack()
		frame.show()
	}


	static main(args) {
		FChart chart = new FChart()
		//chart.drawPie()
		chart.drawTimeSeries()
		File file = new File("c:/testjpg.jpg")
		ChartUtilities.saveChartAsJPEG(file, chart.chart, ChartPanel.DEFAULT_WIDTH, ChartPanel.DEFAULT_HEIGHT)
	}
}