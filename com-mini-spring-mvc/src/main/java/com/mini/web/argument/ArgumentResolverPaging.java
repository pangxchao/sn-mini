package com.mini.web.argument;

import com.mini.jdbc.util.Paging;
import com.mini.util.TypeUtil;

import javax.annotation.Nonnull;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public final class ArgumentResolverPaging implements ArgumentResolver {
    @Override
    public Object value(@Nonnull String name, @Nonnull Class<?> type, @Nonnull HttpServletRequest request, @Nonnull HttpServletResponse response) throws Exception {
        String page = request.getParameter("page"), rows = request.getParameter("rows");
        return new Paging(TypeUtil.castToIntVal(page), TypeUtil.castToIntVal(rows));
    }

}
