﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace RieltorHelper.Infrastructure
{
    public interface IUnitOfWork
    {
        /*
         * Enumerate all the repo properties
            IRieltorRepository<T> repoProperty
        */
        void Save();
    }
}
