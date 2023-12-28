import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class FinanceTracker  {
    public static void main(String[] args) {
        // Collect user information (name, age, and monthly income)
        String name = JOptionPane.showInputDialog("Enter your name:");
        String ageString = JOptionPane.showInputDialog("Enter your age:");
        int age = Integer.parseInt(ageString);
        String incomeString = JOptionPane.showInputDialog("Enter your monthly income in Rs:");
    //    double income = Double.parseDouble(incomeString);
        // Extend the data structure to include user income and expense limits
        Map<String, Double> expenses = new HashMap<>();
        Map<String, Double> expenseLimits = new HashMap<>();
        for (String category : new String[]{"Food", "Health", "Travel", "Shopping", "Bills"}) {
            String limitString = JOptionPane.showInputDialog("Set a limit for " + category + " in Rs:");
            double limit = Double.parseDouble(limitString);
            expenseLimits.put(category, limit);

            String expenseString = JOptionPane.showInputDialog("Enter your monthly expense for " + category + " in Rs:");
            double expense = Double.parseDouble(expenseString);
            expenses.put(category, expense);
        }

        // Create a dataset for the chart
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        BarRenderer renderer = null;
        for (String category : expenses.keySet()) {
            double limit = expenseLimits.get(category);
            double expense = expenses.get(category);
            // Add both expense and limit to the dataset
            dataset.addValue(expense, "Expense", category);
            dataset.addValue(limit, "Limit", category);

            // Determine the bar color based on expense and limit
            renderer = new BarRenderer();
            renderer.setItemMargin(0.1);
            if (expense > limit) {
                renderer.setSeriesPaint(0, Color.RED); // Expense exceeds the limit
            } else if (expense== limit) {
                renderer.setSeriesPaint(0, Color.BLUE); // Expense equals the limit
            } else if (expense<limit){
                renderer.setSeriesPaint(0, Color.GREEN); // Expense is less than the limit
            }
        }

        // Create a bar chart
        JFreeChart chart = ChartFactory.createBarChart(
                "Monthly Expenses for " + name + " (Age: " + age + ")", // Chart title
                "Categories", // X-axis label
                "Expense (in Rs)", // Y-axis label
                dataset);
        CategoryPlot plot = chart.getCategoryPlot();
        plot.setRenderer(renderer);

        // Display the chart in a GUI window
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(800, 600));
        JFrame frame = new JFrame("Expense Tracker");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(chartPanel);
        frame.pack();
        frame.setVisible(true);
        // Display the messages for exceeded limits
        for (String category : expenses.keySet()) {
            double limit = expenseLimits.get(category);
            double expense = expenses.get(category);
            if (expense > limit) {
                JOptionPane.showMessageDialog(frame, "Hey " + name + ", you have exceeded the limit for " + category +

                        " by " + (expense - limit) + " Rs.");
            }
        }
    }
}
