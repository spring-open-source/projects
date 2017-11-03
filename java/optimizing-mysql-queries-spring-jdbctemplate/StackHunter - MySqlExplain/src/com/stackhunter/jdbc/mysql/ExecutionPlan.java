package com.stackhunter.jdbc.mysql;

public class ExecutionPlan {

    private long id;
    private String selectType;
    private String table;
//    private String partitions;
    private String type;
    private String possibleKeys;
    private String key;
    private String keyLength;
    private String ref;
    private int rows;
//    private int filtered;
    private String extra;
    private long duration;
    private String sql;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSelectType() {
        return selectType;
    }

    public void setSelectType(String selectType) {
        this.selectType = selectType;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

//    public String getPartitions() {
//        return partitions;
//    }
//
//    public void setPartitions(String partitions) {
//        this.partitions = partitions;
//    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPossibleKeys() {
        return possibleKeys;
    }

    public void setPossibleKeys(String possibleKeys) {
        this.possibleKeys = possibleKeys;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getKeyLength() {
        return keyLength;
    }

    public void setKeyLength(String keyLength) {
        this.keyLength = keyLength;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

//    public int getFiltered() {
//        return filtered;
//    }
//
//    public void setFiltered(int filtered) {
//        this.filtered = filtered;
//    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }
    
    public long getDuration() {
        return duration;
    }
    
    public void setDuration(long duration) {
        this.duration = duration;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public static String toHeaderTSV() {
        return "ID\tSelectType\tTable\tType\tPossibleKeys\tKey\tKeyLength\tRef\tRows\tExtra\tDuration\tSQL";
    }

    public String toTSV() {
        return id + "\t" + selectType + "\t" + table + "\t" /*+ partitions + "\t"*/ + type + "\t" + possibleKeys + "\t" + key + "\t"
                + keyLength + "\t" + ref + "\t" + rows + "\t" /*+ filtered + "\t"*/ + extra + "\t" + (duration/1000.0) + "\t" + sql;
    }

    @Override
    public String toString() {
        return toTSV();
    }

}
