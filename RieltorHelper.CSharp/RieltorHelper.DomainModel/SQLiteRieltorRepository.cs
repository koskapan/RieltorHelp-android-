﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using RieltorHelper.Infrastructure;

namespace RieltorHelper.DomainModel
{
    public class SQLiteRieltorRepository : IRieltorRepository
    {
        public void CreateTable(IDataTable table)
        {
            throw new NotImplementedException();
        }

        public void DeleteTable(int id)
        {
            throw new NotImplementedException();
        }

        public void EditTable(int id, IDataTable table)
        {
            throw new NotImplementedException();
        }

        public IDataTable GetTable(int id)
        {
            throw new NotImplementedException();
        }

        public IEnumerable<IDataTable> GetTables()
        {
            throw new NotImplementedException();
        }
    }
}
