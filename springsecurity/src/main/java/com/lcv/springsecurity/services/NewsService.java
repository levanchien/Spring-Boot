package com.lcv.springsecurity.services;

import com.lcv.springsecurity.models.News;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class NewsService {

    private List<News> data = new ArrayList<>();

    public NewsService() {
        fakeData();
    }

    private void fakeData() {
        if (data.size() == 0) {
            data.add(new News(1, "Ngoc Trinh lo vong 1", "Ngoc Trinh lo vong 1 cuc khung", "Jetty"));
            data.add(new News(2, "Avenger", "Captain American cong chieu chinh thuc", "King"));
            data.add(new News(3, "People", "People awesome around the world", "Ivan"));
        }
    }

    public List<News> get() {
        return data;
    }

    public int delete(int id) {
        Optional<News> optional = isExist(id);
        if (optional.isPresent()) {
            data.remove(optional.get());
            return 1;
        } else {
            return -1;
        }

    }

    private Optional<News> isExist(int id) {
        return data.stream().filter(news -> news.getId() == id).findFirst();
    }
}
