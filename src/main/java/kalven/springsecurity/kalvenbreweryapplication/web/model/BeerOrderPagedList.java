package kalven.springsecurity.kalvenbreweryapplication.web.model;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @Project kalven-brewery-application
 * @Author kalvens on 2/20/23
 */
public class BeerOrderPagedList extends PageImpl<BeerOrderDto> {
    public BeerOrderPagedList(List<BeerOrderDto> content, Pageable pageable, long total) {
        super(content, pageable, total);
    }
    public BeerOrderPagedList(List<BeerOrderDto> content) {
        super(content);
    }
}
