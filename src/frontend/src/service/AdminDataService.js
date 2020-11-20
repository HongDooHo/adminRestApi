import http from "./http-common";

class AdminDataService {
    getAll() {
        return http.get("/admin/all");
    }

    get(id) {
        return http.get(`/admin/${id}`);
    }

    create(data) {
        return http.post("/admin", data);
    }

    update(data) {
        return http.put(`/admin`, data);
    }

    delete(id) {
        return http.delete(`/admin/${id}`);
    }
}

export default new AdminDataService();