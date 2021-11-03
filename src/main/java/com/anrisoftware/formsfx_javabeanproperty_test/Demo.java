package com.anrisoftware.formsfx_javabeanproperty_test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import com.anrisoftware.formsfx_javabeanproperty_test.ObservableGameMainPaneProperties.GameMainPaneProperties;
import com.dlsc.formsfx.model.structure.Field;
import com.dlsc.formsfx.model.structure.Form;
import com.dlsc.formsfx.model.structure.Group;
import com.dlsc.formsfx.view.renderer.FormRenderer;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Demo extends Application {

    private Form form;

    private GameMainPaneProperties p;

    private ObservableGameMainPaneProperties np;

    private BorderPane rootPanel;

    @Override
    public void start(Stage primaryStage) throws Exception {
        p = new ObservableGameMainPaneProperties.GameMainPaneProperties();
        np = new ObservableGameMainPaneProperties(p);
        this.form = Form.of(Group.of(//
                Field.ofStringType(np.kernelName).label("Name").required("Not empty"), //
                Field.ofIntegerType(np.seed).label("Seed").required("Not empty"), //
                Field.ofIntegerType(np.width).label("Width").required("Not empty"), //
                Field.ofIntegerType(np.height).label("Height").required("Not empty"), //
                Field.ofDoubleType(np.z).label("Z").required("Not empty"), //
                Field.ofIntegerType(np.dim).label("Dimension").required("Not empty") //
        ));
        rootPanel = new BorderPane();
        rootPanel.setCenter(new FormRenderer(form));
        Scene scene = new Scene(rootPanel);
        primaryStage.setTitle("Test");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void stop() throws Exception {
        System.out.println("Manual change");
        np.fileName.set("new-value");
        assertThat(np.fileName.get(), equalTo("new-value"));
        assertThat(p.fileName, equalTo("new-value"));
        System.out.println(p.toString());
        assertThat(np.dim.get(), equalTo(4));
        assertThat(p.dim, equalTo(4));
    }

    public static void main(String[] args) {
        launch(args);
    }
}
