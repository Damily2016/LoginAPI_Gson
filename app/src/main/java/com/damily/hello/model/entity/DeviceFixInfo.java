package com.damily.hello.model.entity;

import java.util.List;

/**
 * Created by Dandan.Cao on 2016/7/27.
 */
public class DeviceFixInfo {

    private boolean status;

    private DeviceFixMessage message;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public DeviceFixMessage getMessage() {
        return message;
    }

    public void setMessage(DeviceFixMessage message) {
        this.message = message;
    }

    public static class DeviceFixMessage {
        private int recordsCount;

        private List<DeviceFix> records;

        public int getRecordsCount() {
            return recordsCount;
        }

        public void setRecordsCount(int recordsCount) {
            this.recordsCount = recordsCount;
        }

        public List<DeviceFix> getRecords() {
            return records;
        }

        public void setRecords(List<DeviceFix> records) {
            this.records = records;
        }

        public static class DeviceFix {
            private int maintainId;
            private String deviceRegId;
            private String typeName;
            private int status;
            private String directory;
            private String img;
            private String imgAlias;
            private String diagnosis;
            private String submitTime;
            private String submitUser;
            private String confirmTime;
            private int deviceId;
            private String directoryIP;

            public int getMaintainId() {
                return maintainId;
            }

            public void setMaintainId(int maintainId) {
                this.maintainId = maintainId;
            }

            public String getDeviceRegId() {
                return deviceRegId;
            }

            public void setDeviceRegId(String deviceRegId) {
                this.deviceRegId = deviceRegId;
            }

            public String getTypeName() {
                return typeName;
            }

            public void setTypeName(String typeName) {
                this.typeName = typeName;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public String getDirectory() {
                return directory;
            }

            public void setDirectory(String directory) {
                this.directory = directory;
            }

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public String getImgAlias() {
                return imgAlias;
            }

            public void setImgAlias(String imgAlias) {
                this.imgAlias = imgAlias;
            }

            public String getDiagnosis() {
                return diagnosis;
            }

            public void setDiagnosis(String diagnosis) {
                this.diagnosis = diagnosis;
            }

            public String getSubmitTime() {
                return submitTime;
            }

            public void setSubmitTime(String submitTime) {
                this.submitTime = submitTime;
            }

            public String getSubmitUser() {
                return submitUser;
            }

            public void setSubmitUser(String submitUser) {
                this.submitUser = submitUser;
            }

            public String getConfirmTime() {
                return confirmTime;
            }

            public void setConfirmTime(String confirmTime) {
                this.confirmTime = confirmTime;
            }

            public int getDeviceId() {
                return deviceId;
            }

            public void setDeviceId(int deviceId) {
                this.deviceId = deviceId;
            }

            public String getDirectoryIP() {
                return directoryIP;
            }

            public void setDirectoryIP(String directoryIP) {
                this.directoryIP = directoryIP;
            }
        }
    }
}
