public abstract class ContainerBase
  implements Container, Lifecycle, Pipeline,
  MBeanRegistration, Serializable{

    /**
    * The processor delay for this component.
    */
    protected int backgroundProcessorDelay = -1;

    /**
    * The lifecycle event support for this component.
    */
    protected LifecycleSupport lifecycle =
      new LifecycleSupport(this);

    /**
    * The container event listeners for this Container.
    */
    protected ArrayList listeners = new ArrayList();

    /**
    * The logger implementation with which this Container is
    * associated.
    */
    protected Log logger - null;

    /**
    * Associated logger name.
    */
    protected String logName = null;
    /**
    * The Manager implementation with which this Container is
    * associated.
    */
    protected Manager manager = null;

    /**
    * The cluster whith which this Container is associated.
    */
    protected Cluster cluster = null;

    /**
    * The human-readable name of this Container.
    */
    protected String name = null;

    /**
    * The parent class loader to be configured when we install a
    * Loader.
    */
    protected ClassLoader parentClassLoader = null;

    /**
    * The Pipeline object with which this Container is
    * associated.
    */
    protected Pipeline pipeline = new StandardPipeline(this);

    /**
    * The realm with which this Container is associated.
    */
    protected Realm realm = null;

    /**
    * The resources DirContext object with which this Container
    * is associated.
    */
    protected DirContext resources = null;
  }
