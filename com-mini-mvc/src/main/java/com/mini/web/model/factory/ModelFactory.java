package com.mini.web.model.factory;

import com.mini.web.model.IModel;
import com.mini.web.view.IView;

public interface ModelFactory<T extends IModel> {
    IModel<T> getModel(IView view, String viewPath );
}