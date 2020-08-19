package com.ozan.exchange.accessor;

import com.ozan.exchange.accessor.resource.FXCurrencyResource;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "fx-accessor", url = "${fx.api-url}")
public interface FXCurrencyAccessor {

    @GetMapping
    FXCurrencyResource getCurrencyInfo(
            @RequestParam(name = "base") String base,
            @RequestParam(name = "symbols") List<String> symbols);
}
