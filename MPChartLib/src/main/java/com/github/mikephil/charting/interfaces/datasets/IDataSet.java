package com.github.mikephil.charting.interfaces.datasets;

import android.graphics.DashPathEffect;
import android.graphics.Typeface;

import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.DataSet;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.model.GradientColor;

import java.util.List;

/**
 * Created by Philipp Jahoda on 21/10/15.
 */
public interface IDataSet<T extends Entry> {

    /** ###### ###### 数据相关方法 ###### ###### */

    /**
     * 返回此DataSet持有的最小y值
     *
     * @return
     */
    float getYMin();

    /**
     * 返回此DataSet持有的最大y值
     *
     * @return
     */
    float getYMax();

    /**
     * returns the minimum x-value this DataSet holds
     *
     * @return
     */
    float getXMin();

    /**
     * returns the maximum x-value this DataSet holds
     *
     * @return
     */
    float getXMax();

    /**
     * 返回此DataSet表示的y值的数量-> y值数组的大小
     * -> yvals.size()
     *
     * @return
     */
    int getEntryCount();

    /**
     * 计算最小和最大x和y值（mXMin，mXMax，mYMin，mYMax）
     */
    void calcMinMax();

    /**
     * 从最接近给定值的条目（从X到最接近给定toX值的条目）计算最小和最大y值。
     * 只有自动缩放最小最大功能才需要。
     *
     * @param fromX
     * @param toX
     */
    void calcMinMaxY(float fromX, float toX);

    /**
     * 使用二进制返回在给定x值处找到的第一个Entry对象
     *       *搜索。
     *       *如果在指定的x值处没有找到条目，则此方法
     *       *根据舍入返回最接近的x值的Entry。
     *       *信息：此方法在运行时进行计算。 做
     *       *在性能至关重要的情况下不要过度使用。
     *       *
     *       * @param xValue x值
     *       * @param最近更新如果指定的x值有多个y值，
     *       * @param舍入确定是否向上/向下/最接近
     *       *如果没有与提供的x值匹配的条目
     * @return
     *
     *
     */
    T getEntryForXValue(float xValue, float closestToY, DataSet.Rounding rounding);

    /**
     * 使用二进制返回在给定x值处找到的第一个Entry对象
     *       *搜索。
     *       *如果在指定的x值处没有找到条目，则此方法
     *       *返回最接近的x值的Entry。
     *       *信息：此方法在运行时进行计算。 做
     *       *在性能至关重要的情况下不要过度使用。
     *       *
     *       *
     *       * @param xValue x值
     *       * @param最近更新如果指定的x值有多个y值，
     * @return
     */
    T getEntryForXValue(float xValue, float closestToY);

    /**
     * Returns all Entry objects found at the given x-value with binary
     * search. An empty array if no Entry object at that x-value.
     * INFORMATION: This method does calculations at runtime. Do
     * not over-use in performance critical situations.
     *
     * @param xValue
     * @return
     */
    List<T> getEntriesForXValue(float xValue);

    /**
     * 返回在values数组中给定索引（NOT xIndex）处找到的Entry对象。
     *
     * @param index
     * @return
     */
    T getEntryForIndex(int index);

    /**
     * Returns the first Entry index found at the given x-value with binary
     * search.
     * If the no Entry at the specified x-value is found, this method
     * returns the Entry at the closest x-value according to the rounding.
     * INFORMATION: This method does calculations at runtime. Do
     * not over-use in performance critical situations.
     *
     * @param xValue the x-value
     * @param closestToY If there are multiple y-values for the specified x-value,
     * @param rounding determine whether to round up/down/closest
     *                 if there is no Entry matching the provided x-value
     * @return
     */
    int getEntryIndex(float xValue, float closestToY, DataSet.Rounding rounding);

    /**
     * Returns the position of the provided entry in the DataSets Entry array.
     * Returns -1 if doesn't exist.
     *
     * @param e
     * @return
     */
    int getEntryIndex(T e);


    /**
     * This method returns the actual
     * index in the Entry array of the DataSet for a given xIndex. IMPORTANT: This method does
     * calculations at runtime, do not over-use in performance critical
     * situations.
     *
     * @param xIndex
     * @return
     */
    int getIndexInEntries(int xIndex);

    /**
     * Adds an Entry to the DataSet dynamically.
     * Entries are added to the end of the list.
     * This will also recalculate the current minimum and maximum
     * values of the DataSet and the value-sum.
     *
     * @param e
     */
    boolean addEntry(T e);


    /**
     * Adds an Entry to the DataSet dynamically.
     * Entries are added to their appropriate index in the values array respective to their x-position.
     * This will also recalculate the current minimum and maximum
     * values of the DataSet and the value-sum.
     *
     * @param e
     */
    void addEntryOrdered(T e);

    /**
     * Removes the first Entry (at index 0) of this DataSet from the entries array.
     * Returns true if successful, false if not.
     *
     * @return
     */
    boolean removeFirst();

    /**
     * Removes the last Entry (at index size-1) of this DataSet from the entries array.
     * Returns true if successful, false if not.
     *
     * @return
     */
    boolean removeLast();

    /**
     * Removes an Entry from the DataSets entries array. This will also
     * recalculate the current minimum and maximum values of the DataSet and the
     * value-sum. Returns true if an Entry was removed, false if no Entry could
     * be removed.
     *
     * @param e
     */
    boolean removeEntry(T e);

    /**
     * Removes the Entry object closest to the given x-value from the DataSet.
     * Returns true if an Entry was removed, false if no Entry could be removed.
     *
     * @param xValue
     */
    boolean removeEntryByXValue(float xValue);

    /**
     * Removes the Entry object at the given index in the values array from the DataSet.
     * Returns true if an Entry was removed, false if no Entry could be removed.
     *
     * @param index
     * @return
     */
    boolean removeEntry(int index);

    /**
     * Checks if this DataSet contains the specified Entry. Returns true if so,
     * false if not. NOTE: Performance is pretty bad on this one, do not
     * over-use in performance critical situations.
     *
     * @param entry
     * @return
     */
    boolean contains(T entry);

    /**
     * Removes all values from this DataSet and does all necessary recalculations.
     */
    void clear();


    /** ###### ###### STYLING RELATED (& OTHER) METHODS ###### ###### */

    /**
     * Returns the label string that describes the DataSet.
     *
     * @return
     */
    String getLabel();

    /**
     * Sets the label string that describes the DataSet.
     *
     * @param label
     */
    void setLabel(String label);

    /**
     * Returns the axis this DataSet should be plotted against.
     *
     * @return
     */
    YAxis.AxisDependency getAxisDependency();

    /**
     * Set the y-axis this DataSet should be plotted against (either LEFT or
     * RIGHT). Default: LEFT
     *
     * @param dependency
     */
    void setAxisDependency(YAxis.AxisDependency dependency);

    /**
     * returns all the colors that are set for this DataSet
     *
     * @return
     */
    List<Integer> getColors();

    /**
     * Returns the first color (index 0) of the colors-array this DataSet
     * contains. This is only used for performance reasons when only one color is in the colors array (size == 1)
     *
     * @return
     */
    int getColor();

    /**
     * Returns the Gradient color model
     *
     * @return
     */
    GradientColor getGradientColor();

    /**
     * Returns the Gradient colors
     *
     * @return
     */
    List<GradientColor> getGradientColors();

    /**
     * Returns the Gradient colors
     *
     * @param index
     * @return
     */
    GradientColor getGradientColor(int index);

    /**
     * Returns the color at the given index of the DataSet's color array.
     * Performs a IndexOutOfBounds check by modulus.
     *
     * @param index
     * @return
     */
    int getColor(int index);

    /**
     * returns true if highlighting of values is enabled, false if not
     *
     * @return
     */
    boolean isHighlightEnabled();

    /**
     * If set to true, value highlighting is enabled which means that values can
     * be highlighted programmatically or by touch gesture.
     *
     * @param enabled
     */
    void setHighlightEnabled(boolean enabled);

    /**
     * Sets the formatter to be used for drawing the values inside the chart. If
     * no formatter is set, the chart will automatically determine a reasonable
     * formatting (concerning decimals) for all the values that are drawn inside
     * the chart. Use chart.getDefaultValueFormatter() to use the formatter
     * calculated by the chart.
     *
     * @param f
     */
    void setValueFormatter(ValueFormatter f);

    /**
     * Returns the formatter used for drawing the values inside the chart.
     *
     * @return
     */
    ValueFormatter getValueFormatter();

    /**
     * Returns true if the valueFormatter object of this DataSet is null.
     *
     * @return
     */
    boolean needsFormatter();

    /**
     * Sets the color the value-labels of this DataSet should have.
     *
     * @param color
     */
    void setValueTextColor(int color);

    /**
     * Sets a list of colors to be used as the colors for the drawn values.
     *
     * @param colors
     */
    void setValueTextColors(List<Integer> colors);

    /**
     * Sets a Typeface for the value-labels of this DataSet.
     *
     * @param tf
     */
    void setValueTypeface(Typeface tf);

    /**
     * Sets the text-size of the value-labels of this DataSet in dp.
     *
     * @param size
     */
    void setValueTextSize(float size);

    /**
     * Returns only the first color of all colors that are set to be used for the values.
     *
     * @return
     */
    int getValueTextColor();

    /**
     * Returns the color at the specified index that is used for drawing the values inside the chart.
     * Uses modulus internally.
     *
     * @param index
     * @return
     */
    int getValueTextColor(int index);

    /**
     * Returns the typeface that is used for drawing the values inside the chart
     *
     * @return
     */
    Typeface getValueTypeface();

    /**
     * Returns the text size that is used for drawing the values inside the chart
     *
     * @return
     */
    float getValueTextSize();

    /**
     * The form to draw for this dataset in the legend.
     * <p/>
     * Return `DEFAULT` to use the default legend form.
     */
    Legend.LegendForm getForm();

    /**
     * The form size to draw for this dataset in the legend.
     * <p/>
     * Return `Float.NaN` to use the default legend form size.
     */
    float getFormSize();

    /**
     * The line width for drawing the form of this dataset in the legend
     * <p/>
     * Return `Float.NaN` to use the default legend form line width.
     */
    float getFormLineWidth();

    /**
     * The line dash path effect used for shapes that consist of lines.
     * <p/>
     * Return `null` to use the default legend form line dash effect.
     */
    DashPathEffect getFormLineDashEffect();

    /**
     * 将其设置为true可以在图表上绘制y值。
     *       *
     *       *注意（对于条形图和折线图）：如果达到了“ maxVisibleCount”，则将不会绘制任何值
     *       *如果已启用
     * @param enabled
     */
    void setDrawValues(boolean enabled);

    /**
     * 如果启用了y值绘图，则返回true；否则，则返回false
     *
     * @return
     */
    boolean isDrawValuesEnabled();

    /**
     * 将其设置为true可以在图表上绘制y-icon。
     *       *
     *       *注意（对于条形图和折线图）：如果达到“ maxVisibleCount”，则即使绘制图标也不会
     *       *如果已启用
     *
     * @param enabled
     */
    void setDrawIcons(boolean enabled);

    /**
     * 如果启用了y-icon绘图，则返回true；否则，则返回false
     *
     * @return
     */
    boolean isDrawIconsEnabled();

    /**
     *图表上绘制的图标的偏移量。
           *
           *对于除Pie和Radar以外的所有图表，它将是普通的（x偏移，y偏移）。
           *
           *对于饼图和雷达图，它将为（y偏移量，距中心偏移量的距离）；
           *因此，如果您希望图标显示在值之下，则应增加CGPoint的X分量，
           *，如果您希望图标在壁橱的中心位置显示，
           *您应降低CGPoint的高度分量。
     * @param offset
     */
    void setIconsOffset(MPPointF offset);

    /**
     * 获取绘图图标的偏移量。
     */
    MPPointF getIconsOffset();

    /**
     * 设置此数据集的可见性。 如果不可见，则DataSet将不会
     *       *刷新后会被绘制到图表上。
     *
     * @param visible
     */
    void setVisible(boolean visible);

    /**
     * 如果此DataSet在图表内可见，则返回true；否则返回false
     *       *当前处于隐藏状态。
     *
     * @return
     */
    boolean isVisible();
}
