package com.fdd.lms.Model;

import org.springframework.stereotype.Component;

/**
 * @author Lin.wang
 * @date 2018-03-10 11:50.
 */
@Component
public class HostHolder {
    private static ThreadLocal<Admin> admin  = new ThreadLocal<Admin>();

    public Admin getAdmin() {
        return admin.get();
    }

    public void setAdmin(Admin ad) {
        admin.set(ad);
    }

    public void clear() {
        admin.remove();;
    }
}
