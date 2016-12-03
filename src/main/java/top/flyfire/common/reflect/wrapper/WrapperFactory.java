package top.flyfire.common.reflect.wrapper;

import top.flyfire.common.Destroy;
import top.flyfire.common.ObjectUtils;
import top.flyfire.common.StringUtils;
import top.flyfire.common.chainedmode.Handler;
import top.flyfire.common.chainedmode.HandlerChain;
import top.flyfire.common.chainedmode.simple.SimpleHandlerChain;
import top.flyfire.common.reflect.MetaInfo;
import top.flyfire.common.reflect.ReflectUtils;
import top.flyfire.common.reflect.ReflectiveException;
import top.flyfire.common.reflect.metainfo.*;
import top.flyfire.common.reflect.value.Parser;
import top.flyfire.common.reflect.value.ValueParserHolder;

import java.lang.reflect.Type;
import java.util.*;

/**
 * Created by shyy_work on 2016/9/13.
 */
public class WrapperFactory {

    private final ValueParserHolder valueParserHolder;

    private final Map<Type, Wrapper> wrapperCached;

    private final SimpleHandlerChain<Wrapper, PMetaContext> pmetaHandlerChain;

    private final SimpleHandlerChain<Wrapper, CMetaContext> cmetaHandlerChain;

    private WrapperFactory() {
        this(ValueParserHolder.getInstance());
    }

    private WrapperFactory(ValueParserHolder valueParserHolder) {
        this.valueParserHolder = valueParserHolder;
        this.wrapperCached = new HashMap<>();
        this.pmetaHandlerChain = SimpleHandlerChain.buildChain(new Handler<Wrapper, PMetaContext>() {
            @Override
            public Wrapper handling(PMetaContext data, HandlerChain<Wrapper, PMetaContext> handlerChain) {
                if (List.class.isAssignableFrom(data.rawType)) {
                    final MetaInfo metaInfo = data.parameterizedMetaInfo.getActualTypeArguments()[0];
                    return new InstanceWrapper<Integer>() {

                        @Override
                        public Object instance() {
                            return new ArrayList<>();
                        }

                        @Override
                        public MetaInfo getMetaInfo(Integer integer) {
                            return metaInfo;
                        }

                        @Override
                        public void set(Integer s, Object instance, Object val) {
                            ((List) instance).add(s, val);
                        }

                        @Override
                        public Object rawValue(Object instance) {
                            return instance;
                        }
                    };
                } else {
                    return handlerChain.handling(data);
                }
            }
        }, new Handler<Wrapper, PMetaContext>() {
            @Override
            public Wrapper handling(PMetaContext data, HandlerChain<Wrapper, PMetaContext> handlerChain) {
                if (Collection.class.isAssignableFrom(data.rawType)) {
                    final MetaInfo metaInfo = data.parameterizedMetaInfo.getActualTypeArguments()[0];
                    return new InstanceWrapper<Integer>() {

                        @Override
                        public Object instance() {
                            return new HashSet<>();
                        }

                        @Override
                        public MetaInfo getMetaInfo(Integer integer) {
                            return metaInfo;
                        }

                        @Override
                        public void set(Integer s, Object instance, Object val) {
                            ((HashSet) instance).add(val);
                        }

                        @Override
                        public Object rawValue(Object instance) {
                            return instance;
                        }
                    };
                } else {
                    return handlerChain.handling(data);
                }
            }
        }, new Handler<Wrapper, PMetaContext>() {
            @Override
            public Wrapper handling(PMetaContext data, HandlerChain<Wrapper, PMetaContext> handlerChain) {
                if (Map.class.isAssignableFrom(data.rawType)) {
                    final MetaInfo metaInfo = data.parameterizedMetaInfo.getActualTypeArguments()[1];
                    return new InstanceWrapper<String>() {
                        @Override
                        public Object instance() {
                            return new HashMap<>();
                        }

                        @Override
                        public MetaInfo getMetaInfo(String s) {
                            return metaInfo;
                        }

                        @Override
                        public void set(String s, Object instance, Object val) {
                            ((Map) instance).put(s, val);
                        }

                        @Override
                        public Object rawValue(Object instance) {
                            return instance;
                        }
                    };
                } else {
                    return handlerChain.handling(data);
                }
            }
        }, new Handler<Wrapper, PMetaContext>() {
            @Override
            public Wrapper handling(PMetaContext data, HandlerChain<Wrapper, PMetaContext> handlerChain) {
                return $wrap(data.parameterizedMetaInfo.asClassMetaInfo());
            }
        });
        this.cmetaHandlerChain = SimpleHandlerChain.buildChain(new Handler<Wrapper, CMetaContext>() {
            @Override
            public Wrapper handling(CMetaContext data, HandlerChain<Wrapper, CMetaContext> handlerChain) {
                final Class rawType = data.rawType;
                if (ReflectUtils.isJdkPrimitiveType(rawType)) {
                    return new Wrapper() {

                        Parser valueParser = WrapperFactory.this.valueParserHolder.apply(rawType);

                        @Override
                        public Object rawValue(Object instance) {
                            return valueParser.parse(instance);
                        }
                    };
                } else {
                    return handlerChain.handling(data);
                }
            }
        }, new Handler<Wrapper, CMetaContext>() {
            @Override
            public Wrapper handling(CMetaContext data, HandlerChain<Wrapper, CMetaContext> handlerChain) {
                final Class rawType = data.rawType;
                if (List.class.isAssignableFrom(rawType)) {
                    return new InstanceWrapper<Integer>() {

                        @Override
                        public Object instance() {
                            return new ArrayList<>();
                        }

                        @Override
                        public MetaInfo getMetaInfo(Integer integer) {
                            return ReflectUtils.unWrap(Object.class);
                        }

                        @Override
                        public void set(Integer s, Object instance, Object val) {
                            ((List) instance).add(s, val);
                        }

                        @Override
                        public Object rawValue(Object instance) {
                            return instance;
                        }
                    };
                } else {
                    return handlerChain.handling(data);
                }
            }
        }, new Handler<Wrapper, CMetaContext>() {
            @Override
            public Wrapper handling(CMetaContext data, HandlerChain<Wrapper, CMetaContext> handlerChain) {
                final Class rawType = data.rawType;
                if (Collection.class.isAssignableFrom(rawType)) {
                    return new InstanceWrapper<Integer>() {

                        @Override
                        public Object instance() {
                            return new HashSet<>();
                        }

                        @Override
                        public MetaInfo getMetaInfo(Integer integer) {
                            return ReflectUtils.unWrap(Object.class);
                        }

                        @Override
                        public void set(Integer s, Object instance, Object val) {
                            ((HashSet) instance).add(val);
                        }

                        @Override
                        public Object rawValue(Object instance) {
                            return instance;
                        }
                    };
                } else {
                    return handlerChain.handling(data);
                }
            }
        }, new Handler<Wrapper, CMetaContext>() {
            @Override
            public Wrapper handling(CMetaContext data, HandlerChain<Wrapper, CMetaContext> handlerChain) {
                final Class rawType = data.rawType;
                if (Map.class.isAssignableFrom(rawType)) {
                    return new InstanceWrapper<String>() {
                        @Override
                        public Object instance() {
                            return new HashMap<>();
                        }

                        @Override
                        public MetaInfo getMetaInfo(String s) {
                            return ReflectUtils.unWrap(Object.class);
                        }

                        @Override
                        public void set(String s, Object instance, Object val) {
                            ((Map) instance).put(s, val);
                        }

                        @Override
                        public Object rawValue(Object instance) {
                            return instance;
                        }
                    };
                } else {
                    return handlerChain.handling(data);
                }
            }
        }, new Handler<Wrapper, CMetaContext>() {
            @Override
            public Wrapper handling(CMetaContext data, HandlerChain<Wrapper, CMetaContext> handlerChain) {
                final ClassMetaInfo classMetaInfo = data.classMetaInfo;
                return new InstanceWrapper<String>() {

                    @Override
                    public Object instance() {
                        return classMetaInfo.newInstance();
                    }

                    @Override
                    public MetaInfo getMetaInfo(String s) {
                        FieldMetaInfo field = classMetaInfo.getField(s);
                        if (field == null) {
                            return MetaInfo.NULL;
                        } else {
                            return field.getType();
                        }
                    }

                    @Override
                    public void set(String s, Object instance, Object val) {
                        FieldMetaInfo field = classMetaInfo.getField(s);
                        if (field == null) {
                            throw new ReflectiveException(StringUtils.merge("Property[", s, "] isn't exists..."));
                        } else {
                            field.setValueTo(instance, val);
                        }
                    }

                    @Override
                    public Object rawValue(Object instance) {
                        return instance;
                    }
                };
            }
        });
    }

    public WrapperFactory(ValueParserHolder valueParserHolder, SimpleHandlerChain<Wrapper, PMetaContext> pmetaHandlerChain, SimpleHandlerChain<Wrapper, CMetaContext> cmetaHandlerChain) {
        this.valueParserHolder = valueParserHolder;
        this.pmetaHandlerChain = pmetaHandlerChain;
        this.cmetaHandlerChain = cmetaHandlerChain;
        this.wrapperCached = new HashMap<>();
    }

    public final Wrapper wrap(MetaInfo metaInfo) {
        Wrapper wrapper;
        return ObjectUtils.isNull(wrapper = wrapperCached.get(metaInfo))?
                cached(metaInfo, $wrap(metaInfo)):wrapper;
    }

    private Wrapper cached(Type type, Wrapper cached) {
        wrapperCached.put(type, cached);
        return cached;
    }

    private final Wrapper $wrap(MetaInfo metaInfo) {
        if (metaInfo instanceof ClassMetaInfo) {
            return wrap((ClassMetaInfo) metaInfo);
        } else if (metaInfo instanceof ParameterizedMetaInfo) {
            return wrap((ParameterizedMetaInfo) metaInfo);
        } else if (metaInfo instanceof WildcardMetaInfo) {
            return wrap((WildcardMetaInfo) metaInfo);
        } else if (metaInfo instanceof VariableMetaInfo) {
            return wrap((VariableMetaInfo) metaInfo);
        } else if (metaInfo instanceof ArrayMetaInfo) {
            return wrap((ArrayMetaInfo) metaInfo);
        }
        throw new ReflectiveException();
    }

    private final Wrapper wrap(WildcardMetaInfo wildcardMetaInfo) {
        MetaInfo bound;
        if (!MetaInfo.NULL.equals(bound = wildcardMetaInfo.getLowerBound())) {
            return $wrap(bound);
        } else if (MetaInfo.NULL.equals(bound = wildcardMetaInfo.getUpperBound())) {
            return $wrap(ReflectUtils.unWrap(Object.class));
        } else {
            return $wrap(bound);
        }
    }

    private final Wrapper wrap(VariableMetaInfo variableMetaInfo) {
        MetaInfo bound;
        if (MetaInfo.NULL.equals(bound = variableMetaInfo.getBound())) {
            return $wrap(ReflectUtils.unWrap(Object.class));
        } else {
            return $wrap(bound);
        }
    }

    private final Wrapper wrap(final ArrayMetaInfo arrayMetaInfo) {
        return new InstanceWrapper<Integer>() {
            @Override
            public Object instance() {
                return new ArrayList<>();
            }

            @Override
            public MetaInfo getMetaInfo(Integer integer) {
                return arrayMetaInfo.getGenericComponentType();
            }

            @Override
            public void set(Integer s, Object instance, Object val) {
                ((List) instance).add(s, val);
            }

            @Override
            public Object rawValue(Object instance) {
                return ((List) instance).toArray();
            }
        };
    }

    private final Wrapper wrap(final ParameterizedMetaInfo parameterizedMetaInfo) {
        PMetaContext pMetaContext = new PMetaContext(parameterizedMetaInfo);
        try {
            return pmetaHandlerChain.handling(pMetaContext);
        } finally {
            pMetaContext.destroy();
        }
    }

    private final Wrapper wrap(ClassMetaInfo classMetaInfo) {
        CMetaContext cMetaContext = new CMetaContext(classMetaInfo);
        try {
            return cmetaHandlerChain.handling(cMetaContext);
        } finally {
            cMetaContext.destroy();
        }
    }

    private class PMetaContext implements Destroy {

        ParameterizedMetaInfo parameterizedMetaInfo;

        Class rawType;

        public PMetaContext(ParameterizedMetaInfo parameterizedMetaInfo) {
            this.parameterizedMetaInfo = parameterizedMetaInfo;
            ClassMetaInfo classMetaInfo = (ClassMetaInfo) parameterizedMetaInfo.getRawType();
            this.rawType = classMetaInfo.getRawType();
        }

        @Override
        public void destroy() {
            this.parameterizedMetaInfo = null;
            this.rawType = null;
        }
    }

    private class CMetaContext implements Destroy {
        ClassMetaInfo classMetaInfo;

        Class rawType;

        public CMetaContext(ClassMetaInfo classMetaInfo) {
            this.classMetaInfo = classMetaInfo;
            this.rawType = classMetaInfo.getRawType();
        }

        @Override
        public void destroy() {
            this.classMetaInfo = null;
            this.rawType = null;
        }
    }

    public final static WrapperFactory getInstance() {
        return new WrapperFactory();
    }

    public final static WrapperFactory getInstance(ValueParserHolder valueParserHolder) {
        return new WrapperFactory(valueParserHolder);
    }

}
