package ru.siberian.huawei.LogSaver.managment;

public class FTPServersForUpload {
    private String ip;
    private String login;
    private String password;
    private String path;
    private String nameServer;

    public FTPServersForUpload(String ip, String login, String password, String path, String nameServer) {
        this.ip = ip;
        this.login = login;
        this.password = password;
        this.path = path;
        this.nameServer = nameServer;
    }

    public String getIp() {
        return ip;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getPath() {
        return path;
    }

    public String getNameServer() {
        return nameServer;
    }

    @Override
    public String toString() {
        return "FTPServersForUpload{" +
                "ip='" + ip + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", path='" + path + '\'' +
                ", nameServer='" + nameServer + '\'' +
                '}';
    }
}
