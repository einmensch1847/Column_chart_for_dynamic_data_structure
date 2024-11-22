package com.example.demo;

import org.knowm.xchart.CategoryChart;
import org.knowm.xchart.CategoryChartBuilder;
import org.knowm.xchart.SwingWrapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class main {
    public static float[] m_data;
    public static int m_size;
    public static int m_capacity;

    public static void main(String[] args) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/data_structure", "root", "");

        int numTests = 100;
        int arraySize = 10;

        init(arraySize);
        System.out.println("Initial Array: " + Arrays.toString(m_data));

        long insertTime = measureAverageTime(() -> insert(randomIndex(), randomValue()), numTests);
        long removeTime = measureAverageTime(() -> remove(randomIndex()), numTests);
        long atTime = measureAverageTime(() -> at(randomIndex()), numTests);
        long setTime = measureAverageTime(() -> set(randomIndex(), randomValue()), numTests);
        long findTime = measureAverageTime(() -> find(randomValue()), numTests);
        long maxTime = measureAverageTime(() -> max(), numTests);
        long minTime = measureAverageTime(() -> min(), numTests);
        long sizeTime = measureAverageTime(() -> size(), numTests);


        System.out.println("Insert time: " + insertTime + " ns");
        System.out.println("Remove time: " + removeTime + " ns");
        System.out.println("At time: " + atTime + " ns");
        System.out.println("Set time: " + setTime + " ns");
        System.out.println("Find time: " + findTime + " ns");
        System.out.println("Max time: " + maxTime + " ns");
        System.out.println("Min time: " + minTime + " ns");
        System.out.println("Size time: " + sizeTime + " ns");


        saveExecutionData(connection, arraySize, insertTime, removeTime, atTime, setTime, findTime, maxTime, minTime, sizeTime);


        drawChart(new long[]{insertTime, removeTime, atTime, setTime, findTime, maxTime, minTime, sizeTime});
        drawchart_insert(insert_numbers(connection) , run_num(connection));
        drawchart_remove(remove_numbers(connection),run_num(connection));
        drawchart_at(at_numbers(connection),run_num(connection));
        drawchart_set(set_numbers(connection),run_num(connection));
        drawchart_find(find_numbers(connection),run_num(connection));
        drawchart_max(max_numbers(connection),run_num(connection));



        connection.close();
    }

    public static long[] max_numbers(Connection connection){
        ArrayList<Long> numbers = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT max_time FROM execution_data");
            while (resultSet.next()) {
                numbers.add((long) resultSet.getInt("max_time"));
            }
            long[] numbers2 = new long[numbers.size()];
            for (int i = 0; i < numbers.size(); i++) {
                numbers2[i] = numbers.get(i) ;
            }

            return numbers2;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static long[] find_numbers(Connection connection){
        ArrayList<Long> numbers = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT find_time FROM execution_data");
            while (resultSet.next()) {
                numbers.add((long) resultSet.getInt("find_time"));
            }
            long[] numbers2 = new long[numbers.size()];
            for (int i = 0; i < numbers.size(); i++) {
                numbers2[i] = numbers.get(i) ;
            }

            return numbers2;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static long[] set_numbers(Connection connection){
        ArrayList<Long> numbers = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT set_time FROM execution_data");
            while (resultSet.next()) {
                numbers.add((long) resultSet.getInt("set_time"));
            }
            long[] numbers2 = new long[numbers.size()];
            for (int i = 0; i < numbers.size(); i++) {
                numbers2[i] = numbers.get(i) ;
            }

            return numbers2;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static long[] at_numbers(Connection connection){
        ArrayList<Long> numbers = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT at_time FROM execution_data");
            while (resultSet.next()) {
                numbers.add((long) resultSet.getInt("at_time"));
            }
            long[] numbers2 = new long[numbers.size()];
            for (int i = 0; i < numbers.size(); i++) {
                numbers2[i] = numbers.get(i) ;
            }

            return numbers2;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static long[] remove_numbers(Connection connection){
        ArrayList<Long> numbers = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT remove_time FROM execution_data");
            while (resultSet.next()) {
                numbers.add((long) resultSet.getInt("remove_time"));
            }
            long[] numbers2 = new long[numbers.size()];
            for (int i = 0; i < numbers.size(); i++) {
                numbers2[i] = numbers.get(i) ;
            }

            return numbers2;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static long[] insert_numbers(Connection connection){
        ArrayList<Long> numbers = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT insert_time FROM execution_data");
            while (resultSet.next()) {
                numbers.add((long) resultSet.getInt("insert_time"));
            }
            long[] numbers2 = new long[numbers.size()];
            for (int i = 0; i < numbers.size(); i++) {
                numbers2[i] = numbers.get(i) ;
            }

            return numbers2;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static int[] run_num(Connection connection) {
        ArrayList<Integer> idList = new ArrayList<>();


        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT id FROM execution_data");

            while (resultSet.next()) {
                idList.add(resultSet.getInt("id"));
            }

            int[] run_num = new int[idList.size()];
            for (int i = 0; i < idList.size(); i++) {
                run_num[i] = idList.get(i);
            }

            return run_num;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return new int[0];
    }

    public static void drawchart_max(long[] times , int[] run_num){
        CategoryChart chart = new CategoryChartBuilder().width(800).height(600).title("Max").xAxisTitle("Time").yAxisTitle("Runs").build();
        chart.addSeries("Execution Times" , Arrays.asList(Arrays.stream(run_num).boxed().toArray(Integer[]::new)), Arrays.asList(Arrays.stream(times).boxed().toArray(Long[]::new)));
        new SwingWrapper(chart).displayChart();
    }

    public static void drawchart_find(long[] times , int[] run_num){
        CategoryChart chart = new CategoryChartBuilder().width(800).height(600).title("Find").xAxisTitle("Time").yAxisTitle("Runs").build();
        chart.addSeries("Execution Times" , Arrays.asList(Arrays.stream(run_num).boxed().toArray(Integer[]::new)), Arrays.asList(Arrays.stream(times).boxed().toArray(Long[]::new)));
        new SwingWrapper(chart).displayChart();
    }

    public static void drawchart_set(long[] times , int[] run_num){
        CategoryChart chart = new CategoryChartBuilder().width(800).height(600).title("Set").xAxisTitle("Time").yAxisTitle("Runs").build();
        chart.addSeries("Execution Times" , Arrays.asList(Arrays.stream(run_num).boxed().toArray(Integer[]::new)), Arrays.asList(Arrays.stream(times).boxed().toArray(Long[]::new)));
        new SwingWrapper(chart).displayChart();
    }

    public static void drawchart_at(long[] times , int[] run_num){
        CategoryChart chart = new CategoryChartBuilder().width(800).height(600).title("At").xAxisTitle("Time").yAxisTitle("Runs").build();
        chart.addSeries("Execution Times" , Arrays.asList(Arrays.stream(run_num).boxed().toArray(Integer[]::new)), Arrays.asList(Arrays.stream(times).boxed().toArray(Long[]::new)));
        new SwingWrapper(chart).displayChart();
    }

    public static void drawchart_remove(long[] times , int[] run_num){
        CategoryChart chart = new CategoryChartBuilder().width(800).height(600).title("Remove").xAxisTitle("Time").yAxisTitle("Runs").build();
        chart.addSeries("Execution Times" , Arrays.asList(Arrays.stream(run_num).boxed().toArray(Integer[]::new)), Arrays.asList(Arrays.stream(times).boxed().toArray(Long[]::new)));
        new SwingWrapper(chart).displayChart();
    }

    public static void drawchart_insert(long[] times , int[] run_num){
        CategoryChart chart = new CategoryChartBuilder().width(800).height(600).title("INSERT").xAxisTitle("Time").yAxisTitle("Runs").build();
        chart.addSeries("Execution Times", Arrays.asList(Arrays.stream(run_num).boxed().toArray(Integer[]::new)), Arrays.asList(Arrays.stream(times).boxed().toArray(Long[]::new)));
        new SwingWrapper<>(chart).displayChart();

    }


    public static void drawChart(long[] times) {
        String[] operations = new String[]{
                "Insert", "Remove", "At", "Set", "Find", "Max", "Min", "Size"
        };
        CategoryChart chart = new CategoryChartBuilder().width(800).height(600).title("Execution Times").xAxisTitle("Operations").yAxisTitle("Time (ns)").build();
        chart.addSeries("Execution Time", Arrays.asList(operations), Arrays.stream(times).boxed().toList());
        new SwingWrapper<>(chart).displayChart();
    }

    public static void saveExecutionData(Connection connection, int arraySize, long insertTime, long removeTime, long atTime, long setTime, long findTime, long maxTime, long minTime, long sizeTime) throws SQLException {
        String query = "INSERT INTO execution_data (array_size, insert_time, remove_time, at_time, set_time, find_time, max_time, min_time, size_time) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, arraySize);
        preparedStatement.setLong(2, insertTime);
        preparedStatement.setLong(3, removeTime);
        preparedStatement.setLong(4, atTime);
        preparedStatement.setLong(5, setTime);
        preparedStatement.setLong(6, findTime);
        preparedStatement.setLong(7, maxTime);
        preparedStatement.setLong(8, minTime);
        preparedStatement.setLong(9, sizeTime);
        preparedStatement.executeUpdate();
    }

    public static void init(int size) {
        m_data = new float[size];
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            m_data[i] = random.nextFloat() * 10;
        }
        m_size = size;
        m_capacity = size;
    }

    public static long measureTime(Runnable func) {
        long start = System.nanoTime();
        func.run();
        long end = System.nanoTime();
        return end - start;
    }

    public static long measureAverageTime(Runnable func, int numTests) {
        long totalTime = 0;
        for (int i = 0; i < numTests; i++) {
            totalTime += measureTime(func);
        }
        return totalTime / numTests;
    }

    public static float randomValue() {
        Random random = new Random();
        return random.nextFloat() * 1000;
    }

    public static int randomIndex() {
        Random random = new Random();
        return random.nextInt(m_size);
    }

    public static void insert(int index, float value) {
        validateIndex(index, true);
        if (m_size == m_capacity) {
            float[] newdata = new float[m_capacity + 1];
            for (int i = 0; i < m_size; i++) {
                if (i < index)
                    newdata[i] = m_data[i];
                else
                    newdata[i + 1] = m_data[i];
            }
            newdata[index] = value;
            m_data = newdata;
            m_capacity++;
        }
        m_size++;
    }

    public static void remove(int index) {
        validateIndex(index, false);
        for (int i = index; i < m_size - 1; i++) {
            m_data[i] = m_data[i + 1];
        }
        m_size--;
        m_data[m_size] = 0;
    }

    public static float set(int index, float value) {
        validateIndex(index, false);
        float oldValue = m_data[index];
        m_data[index] = value;
        return oldValue;
    }

    public static float at(int index) {
        validateIndex(index, false);
        return m_data[index];
    }

    public static long find(float value) {
        for (int i = 0; i < m_size; i++) {
            if (m_data[i] == value) {
                return i;
            }
        }
        return -1;
    }

    public static float max() {
        if (m_size == 0) {
            throw new IllegalArgumentException("Array is empty");
        }
        float max = m_data[0];
        for (int i = 1; i < m_size; i++) {
            if (m_data[i] > max) {
                max = m_data[i];
            }
        }
        return max;
    }

    public static float min() {
        if (m_size == 0) {
            throw new IllegalArgumentException("Array is empty");
        }
        float min = m_data[0];
        for (int i = 1; i < m_size; i++) {
            if (m_data[i] < min) {
                min = m_data[i];
            }
        }
        return min;
    }

    public static int size() {
        return m_size;
    }

    private static void validateIndex(int index, boolean insertCall) {
        if ((insertCall && index > m_size) || index >= m_size) {
            throw new IllegalArgumentException("Invalid Index");
        }
    }
}
